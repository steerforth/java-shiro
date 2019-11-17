package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@TableName("sys_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    /**
     * 0为正常;9为删除
     */
    private Integer status;
    private String name;
    /**
     * 字典,改为枚举
     */
    private String sex;
    private String avatar;
    private String account;
    private String password;
    private String salt;
    private Date birthday;
    private String phone;
    private String email;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 多个角色以逗号分隔
     */
    private String roleIds;
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;
}
