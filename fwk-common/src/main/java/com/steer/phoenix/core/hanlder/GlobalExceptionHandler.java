package com.steer.phoenix.core.hanlder;

import com.steer.phoenix.exception.BizException;
import com.steer.phoenix.modular.system.dto.Result;
import com.steer.phoenix.web.RenderUtil;
import com.steer.phoenix.web.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(HttpServletRequest req, HttpServletResponse res, Exception e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            RenderUtil.renderJson(res,Result.errorResult(e.getMessage()));
        }else {
            mv.setViewName("/exception/500");
            mv.addObject("desc",e.getMessage());
        }
        return mv;
    }

    @ExceptionHandler({BizException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView baseRuntimeException(HttpServletRequest req, HttpServletResponse res, BizException e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            RenderUtil.renderJson(res,Result.errorResult(e.getMessage()));
        }else {
            mv.setViewName("/exception/500");
            mv.addObject("desc",e.getMessage());
        }
        return mv;
    }


    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView authenticationException(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            RenderUtil.renderJson(res,Result.errorResult(e.getMessage()));
        }else {
            mv.setViewName("/login");
            //mv.setViewName("forward:/login");
            mv.addObject("desc",e.getMessage());
        }
        return mv;
    }

    @ExceptionHandler({CredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView credentialsException(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            RenderUtil.renderJson(res,Result.errorResult(e.getMessage()));
        }else {
            mv.setViewName("/login");
            mv.addObject("desc",e.getMessage());
            mv.addObject("tips","用户名或密码错误");
        }
        return mv;
    }


}
