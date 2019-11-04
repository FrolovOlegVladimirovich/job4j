package ru.job4j.io.mail;

import java.util.*;

/**
 * Почта [#184458].
 *
 * Имеется n пользователей, каждому из них соответствует список email-ов
 * (всего у всех пользователей m email-ов).
 * Например:
 * user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru
 * user2 ->foo@gmail.com,ups@pisem.net
 * user3 ->xyz@pisem.net,vasya@pupkin.com
 * user4 ->ups@pisem.net,aaa@bbb.ru
 * user5 ->xyz@pisem.net
 *
 * Считается, что если у двух пользователей есть общий email, значит это
 * один и тот же пользователь. Требуется построить
 * и реализовать алгоритм, выполняющий слияние пользователей. На выходе
 * должен быть список пользователей с их email-ами (такой же как на
 * входе).
 * В качестве имени объединенного пользователя можно брать любое из
 * исходных имен. Список email-ов пользователя должен содержать только
 * уникальные email-ы.
 * Параметры n и m произвольные, длина конкретного списка email-ов никак
 * не ограничена.
 * Требуется, чтобы асимптотическое время работы полученного решения было
 * линейным, или близким к линейному.
 *
 * Возможный ответ на задачу в указанном примере:
 * user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru,ups@pisem.net,aaa@bbb.ru
 * user3 ->xyz@pisem.net,vasya@pupkin.com
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 29.10.2019
 * @version 1.0
 */
public class MergeUsers {
    /**
     *  * 1) Преобразует Set пользователей в userList.
     *  * 2) Создает Set уникальных адресов почты.
     *  * 3) Создает очередь уникальных адресов почты.
     *  * 4) Забирает из очереди по одному уникальному адресу.
     *  * 5) Сравнивает уникальный адрес из очереди со списком адресов каждого пользователя.
     *  * В случае совпадения добавляет пользователей в отдельный List foundUsers.
     *  * 6) Если foundUsers > 1, то объединяет адреса всех найденных пользователей с первым,
     *  * удаляя всех найденных пользователей, кроме первого из userList.
     *  * 7) Возвращает результат в HashSet<>(userList).
     * @param users Set пользователей.
     * @return Set уникальных пользователей.
     */
    public Set<User> byTheSameEmails(Set<User> users) {
        List<User> userList = new ArrayList<>(users);
        Set<String> uniqueEmails = new HashSet<>();
        userList.forEach(user -> uniqueEmails.addAll(user.getEmails()));
        Queue<String> mailsQueue = new LinkedList<>(uniqueEmails);
        while (!mailsQueue.isEmpty()) {
            String mail = mailsQueue.poll();
            List<User> foundUsers = new ArrayList<>();
            userList.forEach(user -> {
                if (user.getEmails().contains(mail)) {
                    foundUsers.add(user);
                }
            });
            if (foundUsers.size() > 1) {
                User userToEdit = userList.get(userList.indexOf(foundUsers.get(0)));
                Set<String> mailsToEdit = userToEdit.getEmails();
                for (int i = 1; i < foundUsers.size(); i++) {
                    User userToDelete = foundUsers.get(i);
                    mailsToEdit.addAll(userToDelete.getEmails());
                    userToEdit.setEmails(mailsToEdit);
                    userList.remove(userToDelete);
                }
            }
        }
        return new HashSet<>(userList);
    }
}