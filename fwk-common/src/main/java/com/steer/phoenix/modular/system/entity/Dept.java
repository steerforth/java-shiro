package com.steer.phoenix.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_dept")
@Data
@EqualsAndHashCode(callSuper = true)
public class Dept extends BaseEntity {
    private Long pid;

    private String pids;

    private String simpleName;

    private String fullName;

    private Integer sort;

    private String desc;

    private Long createUserId;

    private Long updateUserId;
    /**
     * 乐观锁
     */
    private Integer version;
}
