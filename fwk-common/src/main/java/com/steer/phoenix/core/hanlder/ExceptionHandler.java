package com.steer.phoenix.core.hanlder;

import com.alibaba.fastjson.JSON;
import com.steer.phoenix.modular.system.dto.Result;
import com.steer.phoenix.modular.system.enums.ResultEnum;
import com.steer.phoenix.core.exception.AuthenticationException;
import com.steer.phoenix.core.exception.BizException;
import com.steer.phoenix.web.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ModelAndView exception(HttpServletRequest req, HttpServletResponse res, Exception e) {
        ModelAndView mv = new ModelAndView();
        if(WebUtil.isAjax(req)) {
            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            Result r = Result.errorResult(ResultEnum.error_500.getCode(),e.getMessage());
            try {
                res.getWriter().write(JSON.toJSONString(r));
            } catch (IOException e1) {
                log.error("java bean对象转json对象IO流出错:{}",e1.getMessage());
            }
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
            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            Result r = Result.errorResult(e.getCode(),e.getMessage());
            try {
                res.getWriter().write(JSON.toJSONString(r));
            } catch (IOException e1) {
                log.error("java bean对象转json对象IO流出错:{}",e1.getMessage());
            }
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
            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            Result r = Result.errorResult(e.getCode(),e.getMessage());
            try {
                res.getWriter().write(JSON.toJSONString(r));
            } catch (IOException e1) {
                log.error("java bean对象转json对象IO流出错:{}",e1.getMessage());
            }
        }else {
            mv.setViewName("session/login");
            //mv.setViewName("forward:/login");
            mv.addObject("desc",e.getMessage());
        }
        return mv;
    }

}
