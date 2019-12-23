package ru.job4j.garbagecollector;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }

    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + '}';
    }
}