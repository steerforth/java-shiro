package com.steer.phoenix.modular.system.controller;

import com.steer.phoenix.controller.BaseController;
import com.steer.phoenix.modular.system.dto.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/open")
public class IndexController extends BaseController {
    @GetMapping("")
    @ResponseBody
    public Result index(){
        return Result.successReult("成功");
    }

    @GetMapping("/test")
    public String hello(){
        return "test";
    }


}
