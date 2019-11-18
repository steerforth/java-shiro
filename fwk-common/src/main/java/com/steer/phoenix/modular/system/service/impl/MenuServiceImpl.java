package com.steer.phoenix.modular.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steer.phoenix.modular.system.entity.Menu;
import com.steer.phoenix.modular.system.mapper.MenuMapper;
import com.steer.phoenix.modular.system.service.MenuService;
import com.steer.phoenix.node.MenuNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements MenuService {
    @Resource
    private MenuMapper mapper;

    @Override
    public List<MenuNode> getMenuByRoleIds(List<Long> roleIds) {
        if (roleIds == null || roleIds.size()==0){
            return new ArrayList<>(0);
        }

        return  MenuNode.buildTree(mapper.getMenuByRoleIds(roleIds));
    }
}
