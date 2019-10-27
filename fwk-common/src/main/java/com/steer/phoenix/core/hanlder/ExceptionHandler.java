package com.steer.phoenix.core.hanlder;

import com.steer.phoenix.exception.AuthenticationException;
import com.steer.phoenix.exception.BizException;
import com.steer.phoenix.modular.system.dto.Result;
import com.steer.phoenix.web.RenderUtil;
import com.steer.phoenix.web.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ModelAndView exception(HttpServletRequest req, HttpServletResponse res, Exception e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            RenderUtil.renderJson(res,Result.errorResult(e.getMessage()));
        }else {
            mv.setViewName("/exception/exception");
            mv.addObject("desc",e.getMessage());
        }
        return mv;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BizException.class})
    public ModelAndView baseRuntimeException(HttpServletRequest req, HttpServletResponse res, BizException e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            RenderUtil.renderJson(res,Result.errorResult(e.getMessage()));
        }else {
            mv.setViewName("/exception/exception");
            mv.addObject("desc",e.getMessage());
        }
        return mv;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({AuthenticationException.class})
    public ModelAndView authenticationException(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            RenderUtil.renderJson(res,Result.errorResult(e.getMessage()));
        }else {
            mv.setViewName("session/login");
            //mv.setViewName("forward:/login");
            mv.addObject("desc",e.getMessage());
        }
        return mv;
    }

}
