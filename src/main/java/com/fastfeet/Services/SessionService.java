package com.fastfeet.Services;

import com.fastfeet.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}
