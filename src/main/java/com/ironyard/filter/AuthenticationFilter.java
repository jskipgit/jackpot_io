package com.ironyard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jasonskipper on 1/23/17.
 */
//@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // check to see if user is logged in
        HttpServletRequest smartReq = ((HttpServletRequest) req);
        if(smartReq.getSession().getAttribute("user") == null){
            // have NOT logged in
            String nextJSP = "/login.jsp";
            RequestDispatcher dispatcher = smartReq.getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(req,resp);
        } else {
            // already logged in
            chain.doFilter(req, resp);
        }
            //



    }

    public void init(FilterConfig config) throws ServletException {

    }

}
