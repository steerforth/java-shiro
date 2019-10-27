package com.steer.phoenix.modular.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steer.phoenix.modular.system.entity.Menu;
import com.steer.phoenix.modular.system.mapper.MenuMapper;
import com.steer.phoenix.modular.system.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements MenuService {
}
