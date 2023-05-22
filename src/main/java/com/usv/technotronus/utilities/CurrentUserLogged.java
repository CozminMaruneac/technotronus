package com.usv.technotronus.utilities;

import com.usv.technotronus.config.CustomOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CurrentUserLogged {

  private CurrentUserLogged() {
    throw new IllegalStateException("Utility class");
  }

  public static String getLoggedUserEmail() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      log.error("An error with logged user occurred");
      throw new UsernameNotFoundException("A problem has occurred. Please try again later!");
    }

    Object principal = authentication.getPrincipal();
    if (principal instanceof CustomOAuth2User) {
      CustomOAuth2User customOAuth2User = (CustomOAuth2User) principal;
      return customOAuth2User.getEmail();
    }

    throw new UsernameNotFoundException("Invalid user type");
  }
}
