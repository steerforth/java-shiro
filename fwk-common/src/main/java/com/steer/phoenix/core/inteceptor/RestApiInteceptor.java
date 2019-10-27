package com.steer.phoenix.core.inteceptor;

import com.steer.phoenix.exception.BizException;
import com.steer.phoenix.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * restful api 拦截器
 */
public class RestApiInteceptor extends HandlerInterceptorAdapter {
    private static final String AUTH_PATH = "/api/auth";

    private static final String AUTH_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
        return check(request, response);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        if (request.getServletPath().equals(AUTH_PATH)) {
            return true;
        }
        final String requestHeader = request.getHeader(AUTH_HEADER);
        String authToken;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = JwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    throw new BizException(10000004);
//                    RenderUtil.renderJson(response, Result.errorResult(700,"token已过期"));
//                    return false;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                throw new BizException(10000006);
//                RenderUtil.renderJson(response, Result.errorResult(700,"toke验证失败"));
//                return false;
            }
        } else {
            //header没有带Bearer字段
            throw new BizException(10000006);
//            RenderUtil.renderJson(response, Result.errorResult(700,"toke验证失败"));
//            return false;
        }
        return true;
    }
}
