package com.steer.phoenix.modular.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steer.phoenix.modular.system.entity.Role;
import com.steer.phoenix.modular.system.mapper.RoleMapper;
import com.steer.phoenix.modular.system.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
    @Override
    public String getSingleRoleName(Long roleId) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.select("name").eq("id",roleId);
        Role role = super.getOne(wrapper);
        return role.getName();
    }
}
