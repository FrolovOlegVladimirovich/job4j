package ru.job4j.crudservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for processing requests with User data model.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UserServlet extends HttpServlet {
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String message = req.getParameter("message");
        if (message != null) {
            writer.append(message).append("<br/>");
        }
        writer.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <title>All users</title>\n")
                .append("</head>\n")
                .append("<p>Пользователи</p>\n")
                .append("<table border=\"1\">\n")
                .append("    <tr>\n")
                .append("        <th>\n")
                .append("            Имя\n")
                .append("        </th>\n")
                .append("        <th>\n")
                .append("            Логин\n")
                .append("        </th>\n")
                .append("        <th>\n")
                .append("            Почта\n")
                .append("        </th>\n")
                .append("    </tr>");
        for (User user: dispatchAction.toFindAll()) {
            writer.append("    <tr>\n")
                    .append("        <td>\n")
                    .append(user.getName())
                    .append("        </td>\n")
                    .append("        <td>\n")
                    .append(user.getLogin())
                    .append("        </td>\n")
                    .append("        <td>\n")
                    .append(user.getEmail())
                    .append("        </td>\n")
                    .append("        <td>\n")
                    .append("            <form action=\"")
                    .append(req.getContextPath())
                    .append("/edit\" method=\"get\">\n")
                    .append("                <input type=\"hidden\" name=\"id\" value=\"")
                    .append(user.getId())
                    .append("\">\n")
                    .append("                <input type=\"hidden\" name=\"name\" value=\"")
                    .append(user.getName())
                    .append("\">\n")
                    .append("                <input type=\"hidden\" name=\"action\" value=\"update\">\n")
                    .append("                <input type='submit' value=\"Редактировать\">\n")
                    .append("            </form>\n")
                    .append("        </td>\n")
                    .append("        <td>\n")
                    .append("            <form action=\"")
                    .append(req.getContextPath())
                    .append("/edit\" method=\"get\">\n")
                    .append("                <input type=\"hidden\" name=\"id\" value=\"")
                    .append(user.getId())
                    .append("\">\n")
                    .append("                <input type=\"hidden\" name=\"action\" value=\"delete\">\n")
                    .append("                <input type='submit' value=\"Удалить\">\n")
                    .append("            </form>\n")
                    .append("        </td>\n")
                    .append("    </tr>");
        }
        writer.append("</table>\n")
                .append("</body>\n")
                .append("</html>")
                .flush();
    }
}