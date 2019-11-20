package parservacancies.sqlru.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parservacancies.Parser;
import parservacancies.Vacancy;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Vacancies parser. Parses all new Java vacancies from www.sql.ru.
 *
 * Parser configuration depends on previous parsing date:
 * - parser gets all new Java vacancies for the current year,
 * if use default constructor without last parsing date;
 * - parser gets only new Java vacancies,
 * created no earlier than the date and time received from the constructor with parameters.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class SiteSQLRu implements Parser {
    private static final Logger LOG = LogManager.getLogger(SiteSQLRu.class.getName());
    private final String url = "https://www.sql.ru/forum/job-offers/";
    private final Map<String, Integer> siteMonthFormat = Map.ofEntries(
            Map.entry("янв", 1),
            Map.entry("фев", 2),
            Map.entry("мар", 3),
            Map.entry("апр", 4),
            Map.entry("май", 5),
            Map.entry("июн", 6),
            Map.entry("июл", 7),
            Map.entry("авг", 8),
            Map.entry("сен", 9),
            Map.entry("окт", 10),
            Map.entry("ноя", 11),
            Map.entry("дек", 12));
    private LocalDateTime lastParsingDate;

    @Override
    public void setLastParsingDate(LocalDateTime lastParsingDate) {
        this.lastParsingDate = lastParsingDate;
    }

    /**
     * Start parsing vacancies.
     * First of all, it analyzes the first page of https://www.sql.ru/forum/job-offers/, then all subsequent pages.
     * The end of parsing depends on lastParsingDate & the number of suitable vacancies found.
     */
    @Override
    public Set<Vacancy> parse() {
        Set<Vacancy> vacancies = new LinkedHashSet<>();
        int i = 0;
        List<Vacancy> resultOfParseFromOnePage = new ArrayList<>();
        LOG.info("Searching for vacancies");
        do {
            resultOfParseFromOnePage.clear();
            try {
                Document page = Jsoup.connect(url + ++i).get();
                Elements vacanciesOnPage = page.getElementsByAttributeValue("class", "forumTable")
                        .first().getElementsByTag("tr")
                        .stream().filter(element ->
                                !(element.text().startsWith("Важно: ") || element.text().startsWith("Тема")))
                        .collect(Collectors.toCollection(Elements::new));
                for (Element vacancy : vacanciesOnPage) {
                    var vacancyElements = getOuterElementsFromVacancy(vacancy);
                    Vacancy vac;
                    if (lastParsingDate != null) {
                        vac = checkLaterThanLastDate(vacancyElements);
                    } else {
                        vac = checkCurrentYear(vacancyElements);
                    }
                    if (vac != null) {
                        resultOfParseFromOnePage.add(vac);
                    }
                }
                vacancies.addAll(resultOfParseFromOnePage);
            } catch (IOException e) {
                LOG.error("URL connection error: ", e.fillInStackTrace());
            }
        } while (resultOfParseFromOnePage.size() != 0);
        return vacancies;
    }

    /**
     * It checks a vacancy elements received from the main page,
     * which match the creation criteria for the current year to finally create an instance of Vacancy.
     *
     * It checks the year of the last message in vacancy
     * and the matching of vacancy name to the Java keyword.
     * If the name and last changes date are successfully verified,
     * then the internal elements are received from vacancy page: creation date and full text of vacancy.
     * If the date the vacancy was created matches the current year,
     * then a Vacancy instance is created to be added to the database.
     *
     * @param vacancyElements: name (topic name), last message date, topic url.
     * @return Vacancy instance or null if vacancy does not fit the conditions
     * @throws IOException URL(vacancy page) connection error.
     */
    private Vacancy checkCurrentYear(Map<String, String> vacancyElements) throws IOException {
        Vacancy result = null;
        String name = vacancyElements.get("name");
        String url = vacancyElements.get("url");
        int vacancyYearOfChanges = convertSiteDateTimeToLocal(vacancyElements.get("lastChangesDate")).getYear();
        int todayYear = LocalDateTime.now().getYear();
        if (vacancyYearOfChanges == todayYear && matchJavaCheck(name)) {
            var innerElements = getInnerElementsFromVacancy(url);
            LocalDateTime vacancyCreationDate = convertSiteDateTimeToLocal(innerElements.get("creationDate"));
            int vacancyYearOfCreation = vacancyCreationDate.getYear();
            if (vacancyYearOfCreation == todayYear) {
                result = new Vacancy(name,
                        Timestamp.valueOf(vacancyCreationDate),
                        innerElements.get("text"),
                        url);
            }
        }
        return result;
    }

    /**
     * It checks a vacancy elements received from the main page according to the last parsing date
     * to finally create an instance of Vacancy.
     *
     * It compares the date of the last vacancy change and the last parsing date.
     * Also it checks if a vacancy name matches the Java keyword.
     * If all verified, it compares vacancy creation date and the last parsing date.
     *
     * @param vacancyElements: name (topic name), last message date, topic url.
     * @return Vacancy instance or null if vacancy does not fit the conditions
     * @throws IOException URL(vacancy page) connection error.
     */
    private Vacancy checkLaterThanLastDate(Map<String, String> vacancyElements) throws IOException {
        Vacancy result = null;
        String name = vacancyElements.get("name");
        String url = vacancyElements.get("url");
        LocalDateTime vacancyLastChangesDate = convertSiteDateTimeToLocal(vacancyElements.get("lastChangesDate"));
        if (vacancyLastChangesDate.compareTo(lastParsingDate) > 0 && matchJavaCheck(name)) {
            var innerElements = getInnerElementsFromVacancy(url);
            LocalDateTime vacancyCreationDate = convertSiteDateTimeToLocal(innerElements.get("creationDate"));
            if (vacancyCreationDate.compareTo(lastParsingDate) > 0) {
                result = new Vacancy(name,
                        Timestamp.valueOf(vacancyCreationDate),
                        innerElements.get("text"),
                        url);
            }
        }
        return result;
    }

    /**
     * Gets the outer elements of vacancy from the main page of the site.
     * @param vacancy Element with necessary attributes.
     * @return Map of element attributes: name, lastChangesDate, url.
     */
    private Map<String, String> getOuterElementsFromVacancy(Element vacancy) {
        Element urlAndName = vacancy.getElementsByAttribute("href").first();
        return Map.of(
                "name", urlAndName.text(),
                "lastChangesDate", vacancy.getElementsByAttributeValue("class", "altCol").last().text(),
                "url", urlAndName.attr("href")
        );
    }

    /**
     * Gets the inner elements from vacancy topic.
     * @param url A vacancy topic URL.
     * @return Map of element attributes: text, creationDate.
     * @throws IOException URL(vacancy page) connection error.
     */
    private Map<String, String> getInnerElementsFromVacancy(String url) throws IOException {
        Element firstMessage = Jsoup.connect(url).get()
                .getElementsByAttributeValue("class", "msgTable").first();
        return Map.of(
                "text", firstMessage.getElementsByAttributeValue("class", "msgBody").last().text(),
                "creationDate", firstMessage.getElementsByAttributeValue("class", "msgFooter").text()
                        .split(" \\[")[0]
        );
    }

    /**
     * Checks if vacancy name matches the Java key word.
     * @param name Vacancy name.
     * @return true if there's a match.
     */
    private boolean matchJavaCheck(String name) {
        return Pattern.compile("(?!java\\W*script)(java)", Pattern.CASE_INSENSITIVE).matcher(name).find();
    }

    /**
     * Converts site date and time format to LocalDateTime.
     * @param siteDateTime Date and time in site format.
     * @return LocalDateTime instance.
     */
    private LocalDateTime convertSiteDateTimeToLocal(String siteDateTime) {
        LocalDateTime result = null;
        String[] splitSiteDateTime = siteDateTime.split(", ");
        String siteDate = splitSiteDateTime[0];
        String siteTime = splitSiteDateTime[1];
        String[] siteDateElements = siteDate.split(" ");
        String[] siteTimeElements = siteTime.split(":");
        if (siteDateElements.length == 3) {
            result = LocalDateTime.of(
                    Integer.valueOf(siteDateElements[2]) + 2000,
                    siteMonthFormat.get(siteDateElements[1]),
                    Integer.valueOf(siteDateElements[0]),
                    Integer.valueOf(siteTimeElements[0]),
                    Integer.valueOf(siteTimeElements[1])
            );
        } else {
            LocalDateTime today = LocalDateTime.now();
            if ("сегодня".equals(siteDate)) {
                result = LocalDateTime.of(
                        today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        Integer.valueOf(siteTimeElements[0]),
                        Integer.valueOf(siteTimeElements[1])
                );
            } else if ("вчера".equals(siteDate)) {
                LocalDateTime yesterday = today.minusDays(1);
                result = LocalDateTime.of(
                        yesterday.getYear(),
                        yesterday.getMonthValue(),
                        yesterday.getDayOfMonth(),
                        Integer.valueOf(siteTimeElements[0]),
                        Integer.valueOf(siteTimeElements[1])
                );
            }
        }
        return result;
    }
}