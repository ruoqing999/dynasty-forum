package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.User;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public interface Authenticator {

    User authenticate(ServletRequest servletRequest, ServletResponse servletResponse);

}
