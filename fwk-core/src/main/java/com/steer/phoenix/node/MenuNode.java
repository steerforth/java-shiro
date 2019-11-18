package com.steer.phoenix.node;

import com.steer.phoenix.enums.MenuType;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MenuNode implements Serializable {
    /**
     * 节点id
     */
    private Long id;

    /**
     * 父节点
     */
    private Long pid;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 按钮级别
     */
    private Integer level;

    /**
     * 按钮的排序
     */
    private Integer sort;

    /**
     * 节点图标
     */
    private String icon;

    /**
     * 节点的url
     */
    private String url;

    /**
     * 子节点的集合
     */
    private List<MenuNode> children;

    private MenuType type;

    /**
     * 构建菜单
     * @return
     */
    public static List<MenuNode> buildTree(List<MenuNode> nodes){
        if (nodes == null || nodes.size() == 0){
            return new ArrayList<>(0);
        }
        List<MenuNode> sortNodes = nodes.stream().filter(n->n.getType().equals(MenuType.Menu)).sorted(Comparator.comparing(MenuNode::getLevel).thenComparing(MenuNode::getSort)).collect(Collectors.toList());

        return buildWithChildren(0L,sortNodes);
    }

    private static List<MenuNode> buildWithChildren(Long pid, List<MenuNode> sortNodes) {
        List<MenuNode> menus = new ArrayList<>();
        for (int i = 0; i < sortNodes.size(); i++) {
            if (sortNodes.get(i).getPid() == pid){
                sortNodes.get(i).setChildren(buildWithChildren(sortNodes.get(i).getId(),sortNodes));
                menus.add(sortNodes.get(i));
            }
        }
        return menus;
    }
}
