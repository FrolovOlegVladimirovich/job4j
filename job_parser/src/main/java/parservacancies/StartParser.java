package parservacancies;

import parservacancies.sqlru.ConnectionDataBase;
import parservacancies.sqlru.config.ConfigFromResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import parservacancies.sqlru.database.DataBaseParser;
import parservacancies.sqlru.parser.SiteSQLRu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * A main class for starting the vacancy parser program.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class StartParser implements Job {
    private static final Logger LOG = LogManager.getLogger(StartParser.class.getName());

    public void start(List<Parser> sites, Date lastDate, DataBase dataBase) {
        Set<Vacancy> vacancies = new LinkedHashSet<>();
        for (Parser site : sites) {
            site.setLastParsingDate(checkLastDate(dataBase, lastDate));
            vacancies.addAll(site.parse());
        }
        int numOfNewVacs = vacancies.size();
        if (numOfNewVacs != 0) {
            LOG.info("{} new vacancies were found", numOfNewVacs);
            dataBase.addVacancies(vacancies);
        } else {
            LOG.info("No new vacancies were found");
        }
    }

    private LocalDateTime checkLastDate(DataBase sql, Date lastDate) {
        LocalDateTime result = null;
        if (lastDate == null) {
            LOG.info("Previous parsing time is unknown");
            int numOfVacs = sql.checkNumOfVacancies();
            if (numOfVacs != 0) {
                LOG.info("The number of vacancies in the database is {}", numOfVacs);
                result = sql.getLastCreationDate().toLocalDateTime();
            } else {
                LOG.info("There're no vacancies in the database");
            }
        } else {
            LOG.info("Previous parsing time is: {}", lastDate);
            result = new Timestamp(lastDate.getTime()).toLocalDateTime();
        }
        return result;
    }

    /**
     * Scheduler uses properties from the config.ConfigFromResources instance to create a program launch schedule.
     * The program starts every day at 12 noon.
     */
    public void parseWithScheduler(List<Parser> sites) {
        Config config = new ConfigFromResources("app.properties");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("sites", sites);
        jobDataMap.put("config", config);
        JobDetail job = newJob(StartParser.class).usingJobData(jobDataMap).build();
        Trigger trigger = newTrigger().withSchedule(cronSchedule(config.getProperty("cron.time"))).build();
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.error("Scheduler error: ", e.fillInStackTrace());
        }
    }

    /**
     * A logic of program execution, depending on the last parsing date or last date of vacancy in the database.
     * If this is first started:
     * it checks for vacancies in the database. If there are no vacancies in the database,
     * full parsing of the site for Java vacancies created in the current year starts.
     * Otherwise, a program receives the date of the last created vacancy from the database.
     * Then the site will be parsed for Java vacancies created later than the date of the last vacancy in the database.
     *
     * If the program is not running for the first time:
     * jobExecutionContext will pass the date and time of the previous parsing.
     * Then the site parsing starts for Java vacancies created later than the previous parsing date.
     *
     * @param jobExecutionContext A context bundle containing handles to various environment information,
     * that is given to a JobDetail instance as it is executed, and to a Trigger instance after the execution completes.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        List<Parser> sites =  (List<Parser>) jobExecutionContext.getMergedJobDataMap().get("sites");
        Date lastDate = jobExecutionContext.getPreviousFireTime();
        Config config = (Config) jobExecutionContext.getMergedJobDataMap().get("config");
        try (Connection connection = new ConnectionDataBase(config).getConnection()) {
            DataBase dataBase = new DataBaseParser(connection);
            start(sites, lastDate, dataBase);
        } catch (SQLException e) {
            LOG.error("Database connection cannot be closed", e);
        }
    }

    /**
     * Start the program.
     * @param args No args.
     */
    public static void main(String[] args) {
        new StartParser().parseWithScheduler(List.of(new SiteSQLRu()));
    }
}