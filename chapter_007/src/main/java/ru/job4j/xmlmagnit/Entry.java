package ru.job4j.xmlmagnit;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Содержит значение field для создания структуры XML.
 * Аннотация @XmlRootElement JavaBeans отмечает корневой элемент в XML.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.11.2019
 * @version 1.0
 */
@XmlRootElement
public class Entry {
    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return field == entry.field;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}