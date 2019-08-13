package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Тест банковских переводов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.08.2019
 * @version 1.0
 */
public class BankTest {
    private Bank bank = new Bank();
    private User oleg = new User("Oleg", "PassportNumOleg");
    private User olya = new User("Olya", "PassportNumOlya");
    private Account accountOlya1 = new Account(10000);

    /**
     * Добавляем двух пользователей.
     * Проверка метода addAccountToUser - добавляем счет пользователю.
     */
    @Before
    public void beforeActions() {
        bank.addUser(oleg);
        bank.addUser(olya);
        bank.addAccountToUser("PassportNumOlya", accountOlya1);
    }

    /**
     * Поиск пользователя по номеру паспорта.
     */
    @Test
    public void getUserByPassportTest() {
        assertThat(bank.getUserByPassport("PassportNumOleg"), is(oleg));
        assertThat(null, is(bank.getUserByPassport("123")));
    }

    /**
     * Удаление пользователя.
     */
    @Test
    public void deleteUserTest() {
        bank.deleteUser(oleg);
        assertThat(bank.getUsers().containsKey("Oleg"), is(false));
    }

    /**
     * Добавление счета пользователю.
     */
    @Test
    public void addAccountToUserTest() {
        assertThat(bank.getUsers().get(olya).get(0), is(accountOlya1));
        bank.addAccountToUser("NoPassport", accountOlya1);
    }

    /**
     * Удаление счета у пользователя.
     */
    @Test
    public void deleteAccountFromUserTest() {
        bank.deleteAccountFromUser("PassportNumOlya", accountOlya1);
        bank.deleteAccountFromUser("NoPassport", accountOlya1);
        assertThat(bank.getUsers().get(olya).size(), is(0));
    }

    /**
     * Выдача списка всех счетов пользователя.
     */
    @Test
    public void getUserAccountsTest() {
        Account accountOlya2 = new Account(11000);
        Account accountOlya3 = new Account(12000);
        bank.addAccountToUser("PassportNumOlya", accountOlya2);
        bank.addAccountToUser("PassportNumOlya", accountOlya3);
        List<Account> result = new ArrayList<>(Arrays.asList(accountOlya1, accountOlya2, accountOlya3));
        assertThat(bank.getUserAccounts("PassportNumOlya"), is(result));
        assertThat(null, is(bank.getUserAccounts("NoPassport")));
    }

    /**
     * Успешный перевод с одного счета на другой.
     */
    @Test
    public void transferMoneyTestOK() {
        Account accountOlya2 = new Account(11000);
        Account accountOlya3 = new Account(12000);
        Account accountOleg1 = new Account();
        bank.addAccountToUser("PassportNumOlya", accountOlya2);
        bank.addAccountToUser("PassportNumOlya", accountOlya3);
        bank.addAccountToUser("PassportNumOleg", accountOleg1);
        boolean positive = bank.transferMoney(
                "PassportNumOlya", Integer.toString(accountOlya3.getRequisites()),
                "PassportNumOleg", Integer.toString(accountOleg1.getRequisites()),
                12000);
        assertThat(positive, is(true));
    }

    /**
     * Неудачный перевод: отсутвует счет с которого производится перевод.
     */
    @Test
    public void transferMoneyTestWhenNoSrcRequisites() {
        Account accountOlya2 = new Account(11000);
        Account accountOlya3 = new Account(12000);
        Account accountOleg1 = new Account();
        bank.addAccountToUser("PassportNumOlya", accountOlya2);
        bank.addAccountToUser("PassportNumOlya", accountOlya3);
        bank.addAccountToUser("PassportNumOleg", accountOleg1);
        boolean result = bank.transferMoney(
                "PassportNumOlya", Integer.toString(0),
                "PassportNumOleg", Integer.toString(accountOleg1.getRequisites()),
                12000);
        assertThat(result, is(false));
    }

    /**
     * Неудачный перевод: отсутвует счет на который производится перевод.
     */
    @Test
    public void transferMoneyTestWhenNoDstRequisites() {
        Account accountOlya2 = new Account(11000);
        Account accountOlya3 = new Account(12000);
        Account accountOleg1 = new Account();
        bank.addAccountToUser("PassportNumOlya", accountOlya2);
        bank.addAccountToUser("PassportNumOlya", accountOlya3);
        bank.addAccountToUser("PassportNumOleg", accountOleg1);
        boolean result = bank.transferMoney(
                "PassportNumOlya", Integer.toString(accountOlya3.getRequisites()),
                "PassportNumOleg", Integer.toString(0),
                12000);
        assertThat(result, is(false));
    }

    /**
     * Неудачный перевод: отсутствуют оба счета.
     */
    @Test
    public void transferMoneyTestWhenNoRequisites() {
        Account accountOlya2 = new Account(11000);
        Account accountOlya3 = new Account(12000);
        Account accountOleg1 = new Account();
        bank.addAccountToUser("PassportNumOlya", accountOlya2);
        bank.addAccountToUser("PassportNumOlya", accountOlya3);
        bank.addAccountToUser("PassportNumOleg", accountOleg1);
        boolean result = bank.transferMoney(
                "PassportNumOlya", Integer.toString(0),
                "PassportNumOleg", Integer.toString(1),
                12000);
        assertThat(result, is(false));
    }

    /**
     * Неудачный перевод: не хватает денег на счете.
     */
    @Test
    public void transferMoneyTestWhenNoMoney() {
        Account accountOlya2 = new Account(11000);
        Account accountOlya3 = new Account(12000);
        Account accountOleg1 = new Account();
        bank.addAccountToUser("PassportNumOlya", accountOlya2);
        bank.addAccountToUser("PassportNumOlya", accountOlya3);
        bank.addAccountToUser("PassportNumOleg", accountOleg1);
        boolean result = bank.transferMoney(
                "PassportNumOlya", Integer.toString(accountOlya3.getRequisites()),
                "PassportNumOleg", Integer.toString(accountOleg1.getRequisites()),
                13000);
        assertThat(result, is(false));
    }
}
