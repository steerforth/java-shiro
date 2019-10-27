package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity{
    String name;
}
