package com.steer.phoenix.modular.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steer.phoenix.modular.system.entity.User;

public interface UserService extends IService<User> {

    User getByAccount(String account);
}
