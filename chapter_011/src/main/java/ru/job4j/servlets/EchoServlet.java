package ru.job4j.servlets;

import ru.job4j.crudservlet.MemoryStore;
import ru.job4j.crudservlet.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EchoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        MemoryStore.INSTANCE.add(user);
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}