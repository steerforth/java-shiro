package com.steer.phoenix.modular.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steer.phoenix.modular.system.entity.User;
import com.steer.phoenix.node.MenuNode;

import java.util.List;

public interface UserService extends IService<User> {

    User getByAccount(String account);

    List<MenuNode> getUserMenuNodes(List<Long> roleList);
}
