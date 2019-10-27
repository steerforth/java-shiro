package com.steer.phoenix.modular.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steer.phoenix.modular.system.entity.Role;

public interface RoleService extends IService<Role> {
    /**
     * 获取角色名字
     * @param roleId
     * @return
     */
    String getSingleRoleName(Long roleId);
}
