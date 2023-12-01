package com.song.basicx.model.page;

import com.song.basicx.model.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasePage<T extends Model> extends Model {

    /**
     * 当前页
     */
    private Integer pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 是否分页
     */
    private Boolean pagination;

    /**
     * 数据列表
     */
    private List<T> items;
}
