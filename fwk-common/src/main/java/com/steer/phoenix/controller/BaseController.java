package com.steer.phoenix.controller;

import com.steer.phoenix.spring.HttpContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class BaseController {
//    protected final String SUFFIX = ".html";
    protected final String REDIRECT = "redirect:";
    protected final String FORWARD = "forward:";

    protected String getPara(String name) {
        return Objects.requireNonNull(HttpContext.getRequest()).getParameter(name);
    }

    protected HttpServletRequest getHttpServletRequest() {
        return HttpContext.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpContext.getResponse();
    }
}
