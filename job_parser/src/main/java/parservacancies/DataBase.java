package parservacancies;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Database interface.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface DataBase {

    /**
     * Adds vacancies to the database.
     * @param vacancies Set of Vacancy instances.
     */
    void addVacancies(Set<Vacancy> vacancies);

    /**
     * @return A number of vacancies in the database.
     */
    int checkNumOfVacancies();

    /**
     * @return The latest vacancy creation date.
     */
    Timestamp getLastCreationDate();
}