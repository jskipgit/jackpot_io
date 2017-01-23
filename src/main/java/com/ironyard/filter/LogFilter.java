package com.ironyard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jasonskipper on 1/23/17.
 */
@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // before
        System.out.println("BEFORE CHAIN...");

        System.out.println("Requested URL:"+((HttpServletRequest) req).getRequestURI());
        chain.doFilter(req, resp);
        System.out.println("AFTER CHAIN...");

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
