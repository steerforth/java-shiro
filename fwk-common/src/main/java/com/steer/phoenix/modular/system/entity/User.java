package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("sys_user")
@Data
public class User extends BaseEntity {
    private String name;
    /**
     * 字典
     */
    private String sex;
    /**
     * 字典
     */
    private String status;
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
}
