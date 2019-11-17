package com.steer.phoenix.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steer.phoenix.modular.system.entity.Menu;
import com.steer.phoenix.node.MenuNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuNode> getMenuByRoleIds(@Param("roleIds") List<Long> roleIds);
}
