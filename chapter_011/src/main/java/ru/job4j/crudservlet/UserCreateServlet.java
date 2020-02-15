package ru.job4j.crudservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        resp.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        User model = new User();
        model.setName(req.getParameter("name"));
        model.setLogin(req.getParameter("login"));
        model.setEmail(req.getParameter("email"));
        model.setCreateDate(new Date());
        resp.sendRedirect(String.format("%s/create.jsp?message=%s",
                req.getContextPath(),
                dispatchAction.toDo("add", model))
        );
    }
}