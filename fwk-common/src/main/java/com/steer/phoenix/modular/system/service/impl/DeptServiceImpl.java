package com.steer.phoenix.modular.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steer.phoenix.modular.system.entity.Dept;
import com.steer.phoenix.modular.system.mapper.DeptMapper;
import com.steer.phoenix.modular.system.service.DeptService;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper,Dept> implements DeptService {
}
