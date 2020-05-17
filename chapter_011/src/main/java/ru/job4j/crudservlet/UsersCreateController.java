package ru.job4j.crudservlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import ru.job4j.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Servlet to create users in the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UsersCreateController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersCreateController.class.getName());
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Map<String, FileItem> items = Util.getInstance().parse(this, req);
        User model = new User();
        FileItem image = items.get("image");
        model.setName(items.get("name").getString());
        model.setLogin(items.get("login").getString());
        model.setEmail(items.get("email").getString());
        model.setPassword(items.get("password").getString());
        model.setRole(items.get("role").getString());
        model.setCreateDate(new Date());
        model.setCountry(items.get("country").getString());
        model.setCity(items.get("city").getString());
        String photoId;
        if ("".equals(image.getName())) {
            photoId = "";
        } else {
            String extension = "." + FilenameUtils.getExtension(image.getName());
            photoId = image.getName().replace(extension, String.valueOf(model.hashCode()))
                    + extension;
        }
        model.setPhotoId(photoId);
        String addResult = dispatchAction.toDo("add", model);
        boolean result = false;
        LOG.info(addResult);
        JSONObject json = new JSONObject();
        if (addResult.contains("successfully")) {
            saveImage(photoId, image);
            result = true;
            String id = addResult.split("ID ")[1];
            json.put("id", id.substring(0, id.length() - 1));
            json.put("pic", photoId);
        }
        json.put("result", result);
        writer.append(json.toString());
        writer.flush();
    }

    /**
     * Saves the user's image on the server.
     * @param name Unique image name.
     * @param image FileItem image.
     */
    private void saveImage(String name, FileItem image) {
        File folder = new File("images");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder + File.separator + name);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(image.getInputStream().readAllBytes());
        } catch (IOException e) {
            LOG.error("Error saving file on server");
        }
    }
}