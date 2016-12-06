package com.ac.util;

import com.ac.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by Jbee on 2016. 10. 22..
 */
public class HttpSessionUtils {
    public static final String SESSION_KEY = "sessionUser";

    public static boolean isLoginUser(HttpSession session) {
        User sessionUser = (User) session.getAttribute(SESSION_KEY);
        if (sessionUser == null) {
            return false;
        }
        return true;
    }

    public static User getUserFromSession(HttpSession session) {
        User sessionUser = (User) session.getAttribute(SESSION_KEY);
        return sessionUser;
    }
}
