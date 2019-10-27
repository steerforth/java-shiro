package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_menu")
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {
}
