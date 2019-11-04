package ru.job4j.io.mail;

import java.util.Objects;
import java.util.Set;

/**
 * Модель User для приложения MergeUsers.
 *
 * name - имя пользователя может быть не уникально.
 * emails - список почтовых адресов уникальных для каждого пользователя.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 29.10.2019
 * @version 1.0
 */
public class User {
    private final String name;
    private Set<String> emails;

    public User(String name, Set<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public void addEmail(String email) {
        this.emails.add(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(emails, user.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emails);
    }
}