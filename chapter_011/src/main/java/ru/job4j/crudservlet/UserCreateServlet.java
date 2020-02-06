package ru.job4j.crudservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet to create users in the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UserCreateServlet extends HttpServlet {
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <title>Create user</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("Введите данные нового пользователя<br/>\n")
                .append("<form method='post'>\n")
                .append("    <p>Имя:</p><input type='text' name='name'/>\n")
                .append("    <p>Логин:</p><input type='text' name='login'/>\n")
                .append("    <p>Почта:</p><input type='text' name='email'/>\n")
                .append("    <p><input type='submit'/></p>\n")
                .append("</form>\n")
                .append("</body>\n")
                .append("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User model = new User();
        model.setName(req.getParameter("name"));
        model.setLogin(req.getParameter("login"));
        model.setEmail(req.getParameter("email"));
        model.setCreateDate(new Date());
        writer.append(dispatchAction.toDo("add", model))
                .append("<br/><br/>")
                .flush();
        doGet(req, resp);
    }
}