package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.steer.phoenix.modular.system.enums.MenuType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_menu")
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    private Integer status;

    private Long parentId;

    private String code;
    //TODO parentId
    private String pcode;

    private String pcodes;

    private String name;

    private String icon;

    private String url;
    /**
     * 菜单层级
     */
    private Integer level;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String desc;

    private MenuType type;

    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;
}
