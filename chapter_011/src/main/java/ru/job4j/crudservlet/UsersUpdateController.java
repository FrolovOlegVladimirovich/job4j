package ru.job4j.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Servlet for updating user data or removing a user from the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UsersUpdateController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersUpdateController.class.getName());
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        if ("delete".equals(action) || "update".equals(action)) {
            User model = new User();
            model.setId(req.getParameter("id"));
            model.setName(req.getParameter("name"));
            model.setRole(req.getParameter("role"));
            LOG.info(dispatchAction.toDo(action, model));
            String path = new File("images").getAbsolutePath() + File.separator + req.getParameter("photoId");
            new File(path).delete();
            req.setAttribute("users", dispatchAction.toFindAll());
            User session = (User) req.getSession().getAttribute("model");
            if (model.getId().equals(session.getId())) {
                session.setRole(model.getRole());
                session.setName(model.getName());
            }
            req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
        } else if ("updateView".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
        }
    }
}