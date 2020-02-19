package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Servlet to create users in the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UsersCreateController extends HttpServlet {
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        User model = new User();
        model.setName(req.getParameter("name"));
        model.setLogin(req.getParameter("login"));
        model.setEmail(req.getParameter("email"));
        model.setCreateDate(new Date());
        req.setAttribute("message", dispatchAction.toDo("add", model));
        doGet(req, resp);
    }
}