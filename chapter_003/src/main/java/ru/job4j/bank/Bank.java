package ru.job4j.bank;

import java.util.*;

/**
 * Банковские переводы.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.08.2019
 * @version 1.0
 */
public class Bank {
    private Map<User, List<Account>> users = new HashMap<>();

    public Map<User, List<Account>> getUsers() {
        return users;
    }

    /**
     * Находит User по номеру паспорта.
     * @param passport Номер паспорта.
     * @return User.
     */
    public User getUserByPassport(String passport) {
        return this.users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst().orElse(null);
    }

    /**
     * Добавляет User в базу HashMap.
     * @param user User.
     */
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Удаляет User из базы HashMap.
     * @param user User.
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Добавляет счет пользователю.
     * @param passport Номер паспорта.
     * @param account Счет Account.
     */
    public void addAccountToUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null) {
            this.users.get(user).add(account);
        } else {
            System.out.println("Пользователь не найден!");
        }
    }

    /**
     * Удаляет счет пользователя.
     * @param passport Номер паспорта.
     * @param account Счет Account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null) {
            this.users.get(user).remove(account);
        } else {
            System.out.println("Пользователь не найден!");
        }
    }

    /**
     * Возвращает список счетов пользователя.
     * @param passport Номер паспорта.
     * @return Список счетов пользователся List Account.
     */
    public List<Account> getUserAccounts(String passport) {
        return this.users.get(getUserByPassport(passport));
    }

    /**
     * Возвращает счет пользователя, найденный по реквизитам.
     * @param passport Номер паспорта.
     * @param requisites Реквизиты счета.
     * @return Account пользователя.
     */
    public Account getUserAccount(String passport, String requisites) {
    return this.users.get(getUserByPassport(passport))
            .stream()
            .filter(account -> account.getRequisites() == Integer.parseInt(requisites))
            .findFirst().orElse(null);
    }

    /**
     * Перевод суммы с одного счета на другой.
     * @param srcPassport Номер паспорта пользователя, со счета которого будет перевод.
     * @param srcRequisite Реквизиты счета, с которого будет перевод.
     * @param dstPassport Номер паспорта пользователя, на счет которого будет перевод.
     * @param dstRequisite Реквизиты счета, на который будет перевод.
     * @param amount Сумма перевода.
     * @return true, если перевод успешен.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getUserAccount(srcPassport, srcRequisite);
        Account dstAccount = getUserAccount(dstPassport, dstRequisite);
        if (srcAccount != null && dstAccount != null) {
            if (srcAccount.getValue() >= amount) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                dstAccount.setValue(dstAccount.getValue() + amount);
                result = true;
            }
        }
        return result;
    }
}
