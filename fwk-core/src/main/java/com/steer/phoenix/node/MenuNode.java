package com.steer.phoenix.node;

import com.steer.phoenix.enums.MenuType;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private Integer level;

//    /**
//     * 按钮级别(Y  N)
//     */
//    private String ismenu;
    private MenuType type;

    /**
     * TODO ？  sort?
     * 按钮的排序
     */
    private Integer sort;

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
//    private List<MenuNode> linkedList = new ArrayList<>();

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * 构建菜单
     * @return
     */
    public static List<MenuNode> buildTree(List<MenuNode> nodes){
        if (nodes == null || nodes.size() == 0){
            return new ArrayList<>(0);
        }
        List<MenuNode> sortNodes = nodes.stream().filter(n->n.getType().equals(MenuType.Menu)).sorted(Comparator.comparing(MenuNode::getLevel).thenComparing(MenuNode::getSort)).collect(Collectors.toList());

        List<MenuNode> rootNodes = getRootNodes(sortNodes);

        return buildWithLevel(sortNodes);
    }

    private static List<MenuNode> getRootNodes(List<MenuNode> sortNodes) {
        return sortNodes.stream().filter(n->n.getParentId() == 0L).collect(Collectors.toList());
    }

    private static List<MenuNode> buildWithLevel(List<MenuNode> sortNodes) {
        //TODO
        return null;
    }
}
