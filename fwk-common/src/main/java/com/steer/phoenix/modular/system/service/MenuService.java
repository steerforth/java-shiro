package com.steer.phoenix.modular.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steer.phoenix.modular.system.entity.Menu;
import com.steer.phoenix.node.MenuNode;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<MenuNode> getMenuByRoleIds(List<Long> roleIds);
}
