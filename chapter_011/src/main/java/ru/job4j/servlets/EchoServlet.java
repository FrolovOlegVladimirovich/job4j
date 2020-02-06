package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EchoServlet extends HttpServlet {
    private final List<String> users = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        for (String login : users) {
            sb.append("<tr><td>");
            sb.append(login);
            sb.append("</td></tr>");
        }
        sb.append("</table>");
        writer.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <title>Title</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<form action='")
                .append(req.getContextPath())
                .append("/echo' method='post'>\n")
                .append("Name : <input type='text' name='login'/>")
                .append("<input type='submit'>")
                .append("</form>\n")
                .append("<br/>")
                .append(sb.toString())
                .append("</body>\n")
                .append("</html>")
                .flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        users.add(req.getParameter("login"));
        doGet(req, resp);
    }
}