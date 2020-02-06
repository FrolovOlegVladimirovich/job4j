package ru.job4j.crudservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for updating user data or removing a user from the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UserUpdateServlet extends HttpServlet {
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        if ("delete".equals(req.getParameter("action"))) {
            doPost(req, resp);
        } else {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("<!DOCTYPE html>\n")
                    .append("<html lang=\"en\">\n")
                    .append("<head>\n")
                    .append("    <meta charset=\"UTF-8\">\n")
                    .append("    <title>Edit user</title>\n")
                    .append("</head>\n")
                    .append("<p>Редактировать данные пользователя</p>\n")
                    .append("<form action=\"")
                    .append(req.getContextPath())
                    .append("/edit\" method='post'>\n")
                    .append("    <p>Имя:</p><input type='text' name='name' value=\"")
                    .append(req.getParameter("name"))
                    .append("\">\n")
                    .append("               <input type=\"hidden\" name=\"id\" value=\"")
                    .append(req.getParameter("id"))
                    .append("\">\n")
                    .append("               <input type=\"hidden\" name=\"action\" value=\"")
                    .append(req.getParameter("action"))
                    .append("\">\n")
                    .append("    <p><input type='submit'/></p>\n")
                    .append("</form>\n")
                    .append("</body>\n")
                    .append("</html>")
                    .flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        User model = new User();
        model.setId(req.getParameter("id"));
        model.setName(req.getParameter("name"));
        resp.sendRedirect(String.format("%s/list?message=%s",
                req.getContextPath(),
                dispatchAction.toDo(req.getParameter("action"), model))
        );
    }
}