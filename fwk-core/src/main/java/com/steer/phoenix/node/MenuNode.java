package com.steer.phoenix.node;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuNode implements Comparable,Serializable {
    /**
     * 节点id
     */
    private Long id;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 按钮级别
     * //TODO levels  or level?
     */
    private Integer levels;

//    /**
//     * 按钮级别(Y  N)
//     */
//    private String ismenu;

    /**
     * TODO ？  sort?
     * 按钮的排序
     */
    private Integer num;

    /**
     * 节点的url
     */
    private String url;

    /**
     * 节点图标
     */
    private String icon;

    /**
     * 子节点的集合
     */
    private List<MenuNode> children;

    /**
     * 查询子节点时候的临时集合
     */
    private List<MenuNode> linkedList = new ArrayList<>();

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
