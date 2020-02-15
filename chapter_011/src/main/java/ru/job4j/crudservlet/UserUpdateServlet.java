package ru.job4j.crudservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        User model = new User();
        model.setId(req.getParameter("id"));
        model.setName(req.getParameter("name"));
        resp.sendRedirect(String.format("%s/list.jsp?message=%s",
                req.getContextPath(),
                dispatchAction.toDo(req.getParameter("action"), model))
        );
    }
}