package com.example.springboot_311.security;

import com.example.springboot_311.model.User;
import com.example.springboot_311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userServiceImp;

    @Autowired
    public LoginSuccessHandler(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    public LoginSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        User user = userServiceImp.getUserByName(authentication.getName());
        httpServletResponse.sendRedirect("/user/" + user.getId());

    }
}