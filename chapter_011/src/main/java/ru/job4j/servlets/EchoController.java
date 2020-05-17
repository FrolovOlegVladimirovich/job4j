package ru.job4j.servlets;

import ru.job4j.crudservlet.MemoryStore;
import ru.job4j.crudservlet.Store;
import ru.job4j.crudservlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EchoController extends HttpServlet {
    private final Store memory = MemoryStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute("users", memory.findAll());
        req.getRequestDispatcher("/WEB-INF/views/echo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        memory.add(user);
        doGet(req, resp);
    }
}