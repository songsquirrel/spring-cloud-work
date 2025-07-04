package com.song.basic_x.model.tree;

import com.song.basic_x.model.Model;
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
