package com.steer.phoenix.core.inteceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * shiro 权限过滤器
 */
@Slf4j
@Deprecated
public class UserFilter extends AccessControlFilter {
    /**
     * Returns <code>true</code> if the request is a
     * {@link #isLoginRequest(ServletRequest, ServletResponse) loginRequest} or
     * if the current {@link #getSubject(ServletRequest, ServletResponse) subject}
     * is not <code>null</code>, <code>false</code> otherwise.
     *
     * @return <code>true</code> if the request is a
     * {@link #isLoginRequest(ServletRequest, ServletResponse) loginRequest} or
     * if the current {@link #getSubject(ServletRequest, ServletResponse) subject}
     * is not <code>null</code>, <code>false</code> otherwise.
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    /**
     * This default implementation simply calls
     * {@link #saveRequestAndRedirectToLogin(ServletRequest, ServletResponse) saveRequestAndRedirectToLogin}
     * and then immediately returns <code>false</code>, thereby preventing the chain from continuing so the redirect may
     * execute.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.warn("{} access denied!!!!",request.getRemoteHost());
        return false;

        //        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
//        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
//
//        /**
//         * 如果是ajax请求则不进行跳转
//         */
//        if (httpServletRequest.getHeader("x-requested-with") != null
//                && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
//            httpServletResponse.setHeader("sessionstatus", "timeout");
//            return false;
//        } else {
//
//            /**
//             * 第一次点击页面
//             */
//            String referer = httpServletRequest.getHeader("Referer");
//            if (referer == null) {
//                saveRequestAndRedirectToLogin(request, response);
//                return false;
//            } else {
//
//                /**
//                 * 从别的页面跳转过来的
//                 */
//                if (ShiroKit.getSession().getAttribute("sessionFlag") == null) {
//                    httpServletRequest.setAttribute("tips", "session超时");
//                    httpServletRequest.getRequestDispatcher("/login").forward(request, response);
//                    return false;
//                } else {
//                    saveRequestAndRedirectToLogin(request, response);
//                    return false;
//                }
//            }
//        }
    }
}
