package com.usv.technotronus.config;

import com.usv.technotronus.features.user.User;
import com.usv.technotronus.features.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

        User user = userService.processUser(oauthUser.getEmail(),
            (String) oauthUser.getAttributes().get("given_name"),
            (String) oauthUser.getAttributes().get("family_name"),
            (String) oauthUser.getAttributes().get("picture"));

        response.sendRedirect("http://localhost:3000");
    }
}