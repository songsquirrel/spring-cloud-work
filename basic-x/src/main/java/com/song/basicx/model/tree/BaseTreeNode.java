package com.song.basicx.model.tree;

import com.song.basicx.model.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
