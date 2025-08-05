package com.boot.basic.utils;


import com.boot.basic.model.tree.BaseTreeNode;

import java.util.*;

/**
 * @description: 树形结构工具类
 * @create: 2021-01-23 15:52
 * @author: song
 **/
public class BaseTreeUtil {

    /**
     * 根据树节点列表，组装树结构数据
     *
     * @param nodeList 节点列表
     * @return 树结构数据
     */
    public static <T extends BaseTreeNode<R, T>, R> List<T> buildTreeByBaseNode(List<T> nodeList) {
        Map<R, List<T>> pidNodeMap = new HashMap<>();
        Set<R> nodeIdSet = new HashSet<>();

        nodeList.forEach(t -> {
            nodeIdSet.add(t.getId());
            pidNodeMap.computeIfAbsent(t.getParentId(), k -> new ArrayList<>()).add(t);
        });

        nodeList.forEach(t -> t.setChildNodeList(pidNodeMap.get(t.getId())));
        for (Map.Entry<R, List<T>> entry : pidNodeMap.entrySet()) {
            if (nodeIdSet.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return new ArrayList<>();
    }
}
