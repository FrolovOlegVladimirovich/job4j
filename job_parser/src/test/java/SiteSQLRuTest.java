import org.junit.Ignore;
import org.junit.Test;
import parservacancies.Config;
import parservacancies.DataBase;
import parservacancies.Parser;
import parservacancies.sqlru.config.ConfigFromResources;
import parservacancies.sqlru.database.DataBaseParser;
import parservacancies.sqlru.parser.SiteSQLRu;
import parservacancies.Vacancy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SiteSQLRuTest {
    private final Config config = new ConfigFromResources("app.properties");

    public Connection init() throws Exception {
        Class.forName(config.getProperty("jdbc.driver"));
        return DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        );

    }

    @Ignore
    @Test
    public void parseWithoutLastDateAndCompareNumOfVacanciesAndLastCreationDate() throws Exception {
        Parser parser = new SiteSQLRu();
        Set<Vacancy> vacancyList = parser.parse();

        int numOfVacanciesExpect =  vacancyList.size();
        int numOfVacanciesResult;
        Timestamp lastCreationExpect = vacancyList.stream().map(Vacancy::getCreationDate).max(Comparator.naturalOrder()).get();
        Timestamp lastCreationResult;
        try (Connection connection = ConnectionRollback.create(this.init())) {
            DataBase sql = new DataBaseParser(connection);
            sql.addVacancies(vacancyList);
            numOfVacanciesResult = sql.checkNumOfVacancies();
            lastCreationResult = sql.getLastCreationDate();
        }

        assertThat(numOfVacanciesResult, is(numOfVacanciesExpect));
        assertThat(lastCreationResult, is(lastCreationExpect));
    }
}