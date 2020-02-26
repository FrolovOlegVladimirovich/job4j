package ru.job4j.crudservlet;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signin")) {
            chain.doFilter(req, resp);
        } else {
            if (request.getSession().getAttribute("model") == null) {
                HttpServletResponse response = (HttpServletResponse) resp;
                response.sendRedirect(String.format("%s/signin", request.getContextPath()));
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}