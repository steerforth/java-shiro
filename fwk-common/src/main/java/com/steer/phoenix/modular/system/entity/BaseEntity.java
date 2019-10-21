package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseEntity<T> extends Model{
    private static final long serialVersionUID = 1L;

    protected Long id;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;
}

