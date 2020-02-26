package ru.job4j.crudservlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Servlet to create users in the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UsersCreateController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersCreateController.class.getName());
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        Map<String, FileItem> items = parse(req);
        User model = new User();
        FileItem image = items.get("image");
        model.setName(items.get("name").getString());
        model.setLogin(items.get("login").getString());
        model.setEmail(items.get("email").getString());
        model.setPassword(items.get("password").getString());
        model.setRole(items.get("role").getString());
        model.setCreateDate(new Date());
        String photoId;
        if ("".equals(image.getName())) {
            photoId = "";
        } else {
            String extension = "." + FilenameUtils.getExtension(image.getName());
            photoId = image.getName().replace(extension, String.valueOf(model.hashCode())) + extension;
        }
        model.setPhotoId(photoId);
        String addResult = dispatchAction.toDo("add", model);
        LOG.info(addResult);
        if (addResult.contains("successfully")) {
            saveImage(photoId, image);
        }
        resp.sendRedirect(req.getContextPath());
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

    /**
     * Parses a request for parameter names and values.
     * @param req Request.
     * @return Map of parameter names and values.
     */
    private Map<String, FileItem> parse(HttpServletRequest req) {
        Map<String, FileItem> parameters = new HashMap<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            for (FileItem item : upload.parseRequest(req)) {
                if (item.isFormField()) {
                    parameters.put(item.getFieldName(), item);
                } else {
                    parameters.put("image", item);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return parameters;
    }
}