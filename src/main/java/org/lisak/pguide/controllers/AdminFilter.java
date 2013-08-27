package org.lisak.pguide.controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 26.08.13
 * Time: 23:24
 */
public class AdminFilter extends OncePerRequestFilter {
    private List<String> allowedAccounts;

    public List<String> getAllowedAccounts() {
        return allowedAccounts;
    }

    public void setAllowedAccounts(List<String> allowedAccounts) {
        this.allowedAccounts = allowedAccounts;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String loginURL = userService.createLoginURL(httpServletRequest.getRequestURI());

        //if user is not logged in or not in the list of allowed users (bean config), redirect to login page
        if(user==null) {
            httpServletResponse.sendRedirect(loginURL);
        } else {

            boolean isAllowed = false;
            for(String u : allowedAccounts) {
                if(u.compareTo(user.getEmail())==0) {
                    isAllowed = true;
                }
            }

            if(!isAllowed) {
                httpServletResponse.sendRedirect(loginURL);

            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
