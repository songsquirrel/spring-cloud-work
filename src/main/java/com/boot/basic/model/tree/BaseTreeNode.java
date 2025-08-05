package com.boot.basic.model.tree;

import com.boot.basic.model.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class BaseTreeNode<T, R> extends Model {

    /**
     * ID
     */
    private T id;

    /**
     * 父节点ID
     */
    private T parentId;

    /**
     * 子节点列表
     */
    private List<R> childNodeList;
}
