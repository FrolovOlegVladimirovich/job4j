package ru.job4j.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public static Util getInstance() {
        return new Util();
    }

    /**
     * Parses a request for parameter names and values.
     * @param req Request.
     * @return Map of parameter names and values.
     */
    public Map<String, FileItem> parse(HttpServlet servlet, HttpServletRequest req) {
        Map<String, FileItem> parameters = new HashMap<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = servlet.getServletConfig().getServletContext();
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
