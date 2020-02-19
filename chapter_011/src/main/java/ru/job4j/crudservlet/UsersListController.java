package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for processing requests with User data model.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UsersListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.setAttribute("users", DBStore.getINSTANCE().findAll());
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }
}