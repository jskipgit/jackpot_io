package com.ironyard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jasonskipper on 1/23/17.
 */
@WebFilter(filterName = "AuthWithRedirectFilter", urlPatterns = "/*")
public class AuthWithRedirectFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // check to see if user is logged in
        HttpServletRequest smartReq = ((HttpServletRequest) req);
        HttpServletResponse smartResp = ((HttpServletResponse) resp);

        boolean tryingToLogIn = smartReq.getRequestURI().contains("login");
        if(!tryingToLogIn && smartReq.getSession().getAttribute("user") == null){
            // have NOT logged in
            smartResp.sendRedirect("/login.jsp");
        } else {
            // already logged in
            chain.doFilter(req, resp);
        }
        //



    }

    public void init(FilterConfig config) throws ServletException {

    }

}
