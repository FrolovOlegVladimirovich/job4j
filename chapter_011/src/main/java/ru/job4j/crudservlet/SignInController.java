package ru.job4j.crudservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInController extends HttpServlet {
    private final Store memory = DBStore.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("signin.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User model = new User();
        model.setLogin(req.getParameter("login"));
        model.setPassword(req.getParameter("password"));
        model = memory.isCredential(model);
        if (model != null) {
            req.getSession().setAttribute("model", model);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            req.getRequestDispatcher("signin.html").forward(req, resp);
        }
    }
}