package com.steer.phoenix.constants;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    String AUTH_PATH = "/api/auth";

    String AUTH_HEADER = "Authorization";
    /**
     * 不需要权限验证的资源表达式
     */
    List<String> NONE_PERMISSION_RES = Arrays.asList("/assets/**", "/open/**", "/login", "/global/sessionError", "/kaptcha", "/error", "/global/error");
}
