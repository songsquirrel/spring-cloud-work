package com.song.basicx.model.page;

import com.song.basicx.model.Model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageReq extends Model {

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
}
