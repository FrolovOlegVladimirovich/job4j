package parservacancies;

import java.sql.Timestamp;
import java.util.Objects;

public class Vacancy {
    private final String name;
    private final String text;
    private final String link;
    private final Timestamp creationDate;

    public Vacancy(String name, Timestamp creationDate, String text, String link) {
        this.name = name;
        this.creationDate = creationDate;
        this.text = text;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "name='"
                + name
                + '\''
                + ", text='" + text + '\''
                + ", link='" + link + '\''
                + ", creationDate=" + creationDate
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return link.equals(vacancy.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }
}