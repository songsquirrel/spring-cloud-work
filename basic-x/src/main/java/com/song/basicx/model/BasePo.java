package com.song.basicx.model;

import com.mybatisflex.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 基础PO类
 */

@Getter
@Setter
public class BasePo extends Model {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 版本
     */
    private Integer version;

}
