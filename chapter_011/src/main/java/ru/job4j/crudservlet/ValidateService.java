package ru.job4j.crudservlet;

import java.util.*;

/**
 * Logic layout.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public enum ValidateService {
    INSTANCE;
    private final Store memory = DBStore.getINSTANCE();

    /**
     * Checks model for the presence of empty/null fields name, login, email.
     * Checks the uniqueness of login and email.
     * @param model User model.
     * @return Response to the servlet.
     */
    public String add(User model) {
        StringBuilder result = new StringBuilder();
        String name = model.getName();
        String login = model.getLogin();
        String email = model.getEmail();
        String photoId = model.getPhotoId();
        Map<String, String> values = new HashMap<>();
        values.put("name", name);
        values.put("login", login);
        values.put("email", email);
        values.put("photo", photoId);
        if (values.containsValue(null) || values.containsValue("")) {
            result.append("Unable to register user. Required fields: name, login, email, photo. Empty fields:");
            values.forEach(
                    (key, value) -> {
                        if (value == null || "".equals(value)) {
                            result.append(String.format("%n%s", key));
                        }
                    }
            );
        } else {
            if (memory.containsLogin(model)) {
                result.append(
                        String.format("User with login \"%s\" already exists. Try a different login.%n", login)
                );
            } else if (memory.containsEmail(model)) {
                result.append(
                        String.format("User with e-mail \"%s\" already exists. Try a different email.%n", email)
                );
            } else {
                User user = memory.add(model);
                result.append(
                        String.format("User %s was successfully created with ID %s.", user.getLogin(), user.getId())
                );
            }
        }
        return result.toString();
    }

    /**
     * Checks model for the presence of empty/null fields id, name.
     * Checks the uniqueness of name and the presence of the user with the given id.
     * @param model User model.
     * @return Response to the servlet.
     */
    public String update(User model) {
        StringBuilder result = new StringBuilder();
        String id = model.getId();
        String name = model.getName();
        Map<String, String> values = new HashMap<>();
        values.put("id", id);
        values.put("name", name);
        if (values.containsValue(null) || values.containsValue("")) {
            result.append("Unable to update user. Required fields: id, name. Empty fields:");
            values.forEach(
                    (key, value) -> {
                        if (value == null || "".equals(value)) {
                            result.append(String.format("%n%s", key));
                        }
                    }
            );
        } else {
            User user = memory.getUserById(model);
            if (user != null) {
                if (!name.equals(user.getName())) {
                    memory.update(model);
                    result.append(
                            String.format("User ID %s was successfully changed name to %s.", id, name)
                    );
                } else {
                    result.append("User names are the same. Try a different name.");
                }
            } else {
                result.append(
                        String.format("User with ID %s doesn't exist. Try another id.", id)
                );
            }
        }
        return result.toString();
    }

    /**
     * Checks model for the presence of empty/null id field.
     * Checks the presence of the user with the given id.
     * @param model User model.
     * @return Response to the servlet.
     */
    public String delete(User model) {
        StringBuilder result = new StringBuilder();
        String id = model.getId();
        if (id == null || "".equals(id)) {
            result.append("Unable to delete user. Required fields: id.");
        } else if (!memory.containsUser(model)) {
            result.append(
                    String.format("User with ID %s doesn't exist. Try another id.", id)
            );
        } else {
            memory.delete(model);
            result.append(
                    String.format("User ID %s was successfully removed", id)
            );
        }
        return result.toString();
    }

    /**
     * @return users collection to the servlet with all found users .
     */
    public Collection<User> findAll() {
        return memory.findAll();
    }

    /**
     * Checks model for the presence of empty/null id field.
     * Checks the presence of the user with the given id.
     * @param model User model.
     * @return Response to the servlet.
     */
    public String findById(User model) {
        StringBuilder result = new StringBuilder();
        String id = model.getId();
        if (id == null || "".equals(id)) {
            result.append("Unable to find user. Required fields: id.");
        } else if (!memory.containsUser(model)) {
            result.append(
                    String.format("User with ID %s doesn't exist. Try another id.", id)
            );
        } else {
            result.append(memory.findById(model));
        }
        return result.toString();
    }
}