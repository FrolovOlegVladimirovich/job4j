package parservacancies;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Parser interface.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface Parser {
    /**
     * Start parsing vacancies.
     */
    Set<Vacancy> parse();

    void setLastParsingDate(LocalDateTime lastParsingDate);
}