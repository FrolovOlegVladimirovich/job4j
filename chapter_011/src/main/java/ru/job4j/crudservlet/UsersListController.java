package ru.job4j.crudservlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Servlet for processing requests with User data model.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class UsersListController extends HttpServlet {
    private final DispatchAction dispatchAction = DispatchAction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("users", dispatchAction.toFindAll());
        var locations = dispatchAction.toFindLocations();
        var countrySet = new TreeSet<>(locations.keySet());
        JSONObject json = new JSONObject();
        json.put("locations", locations);
        req.setAttribute("locations", json.toString());
        req.setAttribute("countrySet", countrySet);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    public static void main(String[] args) {
       final DispatchAction dispatchAction = DispatchAction.getInstance();
        var locations = dispatchAction.toFindLocations();
        var countrySet = new TreeSet<>(locations.keySet());
        JSONObject json = new JSONObject();
        json.put("locations", locations);
        System.out.println(json.toString());
    }
}