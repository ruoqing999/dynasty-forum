package com.ruoqing.dynastyForum.config.filter;

import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.handler.exception.AuthorizationException;
import com.ruoqing.dynastyForum.service.Authenticator;
import jakarta.servlet.*;

import java.io.IOException;

public class LoginFilter implements Filter {

    Authenticator[] authenticators = initAuthenticators();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = tryGetAuthenticatedUser(servletRequest, servletResponse);


        filterChain.doFilter(servletRequest, servletResponse);
    }

    private User tryGetAuthenticatedUser(ServletRequest servletRequest, ServletResponse servletResponse) {
        User user;
        for (Authenticator auth : authenticators) {
            user = auth.authenticate(servletRequest, servletResponse);
            if (null != user) {
                return user;
            }
        }
        throw new AuthorizationException("认证失败");
    }


    private Authenticator[] initAuthenticators() {

        return null;
    }
}
