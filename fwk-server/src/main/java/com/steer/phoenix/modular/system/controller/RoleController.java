package com.steer.phoenix.modular.system.controller;

import com.steer.phoenix.controller.BaseController;
import com.steer.phoenix.modular.system.service.RoleService;
import com.steer.phoenix.modular.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    private static String PREFIX = "/modular/system/role";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 跳转到角色列表页面
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "/role";
    }

}
