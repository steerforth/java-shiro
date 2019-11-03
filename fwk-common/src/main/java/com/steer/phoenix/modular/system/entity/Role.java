package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity{
    String name;
    /**
     * parent id
     */
    String pid;
    Integer sort;
    Integer version;
    @TableField(fill = FieldFill.INSERT)
    Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Long updateUser;
}
