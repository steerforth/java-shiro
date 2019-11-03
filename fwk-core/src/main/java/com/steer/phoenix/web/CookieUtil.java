package com.steer.phoenix.web;

import com.steer.phoenix.spring.HttpContext;

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie[] getCookies(){
        return HttpContext.getRequest().getCookies();
    }

    public static void deleteCookieByName(String cookieName) {
        Cookie[] cookies = getCookies();
        Cookie[] var3 = cookies;
        int len = cookies.length;

        for(int i = 0; i < len; ++i) {
            Cookie cookie = var3[i];
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                HttpContext.getResponse().addCookie(temp);
            }
        }

    }

    public static void deleteAllCookie() {
        Cookie[] cookies = getCookies();
        Cookie[] var2 = cookies;
        int len = cookies.length;

        for(int i = 0; i < len; ++i) {
            Cookie cookie = var2[i];
            Cookie temp = new Cookie(cookie.getName(), "");
            temp.setMaxAge(0);
            HttpContext.getResponse().addCookie(temp);
        }

    }
}
