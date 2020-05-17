package ru.job4j.crudservlet;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signin")) {
            chain.doFilter(req, resp);
        } else {
            if (request.getSession().getAttribute("model") == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("signin.html");
                dispatcher.forward(request, resp);
            } else {
                chain.doFilter(request, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}