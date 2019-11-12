package ru.job4j.xmlmagnit;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Содержит List<Entry> для создания структуры XML.
 * Аннотация @XmlRootElement JavaBeans отмечает корневой элемент в XML.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.11.2019
 * @version 1.0
 */
@XmlRootElement
public class Entries {
    private List<Entry> entry;

    public Entries() {
    }

    public Entries(List<Entry> entry) {
        this.entry = entry;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entries entries1 = (Entries) o;
        return Objects.equals(entry, entries1.entry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry);
    }
}