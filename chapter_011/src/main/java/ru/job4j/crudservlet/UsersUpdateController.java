package ru.job4j.crudservlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import ru.job4j.util.Util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Map<String, FileItem> items = Util.getInstance().parse(this, req);
        String action = items.get("action").getString();
        boolean result = false;
        String updateResult = "";
        User model = new User();
        if ("update".equals(action)) {
            model.setId(items.get("id").getString());
            model.setName(items.get("name").getString());
            model.setRole(items.get("role").getString());
            model.setCountry(items.get("country").getString());
            model.setCity(items.get("city").getString());
            updateResult = dispatchAction.toDo(action, model);
        }
        if ("delete".equals(action)) {
            model.setId(items.get("id").getString());
            model.setPhotoId(items.get("photoId").getString());
            updateResult = dispatchAction.toDo(action, model);
        }
        LOG.info(updateResult);
        if (updateResult.contains("successfully")) {
            result = true;
        }
        JSONObject json = new JSONObject();
        json.put("result", result);
        User session = (User) req.getSession().getAttribute("model");
        if (model.getId().equals(session.getId())) {
            session.setRole(model.getRole());
            session.setName(model.getName());
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json.toString());
        writer.flush();
    }
}