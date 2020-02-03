package ru.job4j.crudservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet for processing requests with User data model.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UserServlet extends HttpServlet {
    private final DispatchAction dispatchAction = new DispatchAction().init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.write(dispatchAction.toFindAll());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User model = new User();
        model.setId(req.getParameter("id"));
        model.setName(req.getParameter("name"));
        model.setLogin(req.getParameter("login"));
        model.setEmail(req.getParameter("email"));
        model.setCreateDate(new Date());
        writer.write(dispatchAction.toDo(req.getParameter("action"), model));
        writer.flush();
    }
}