package com.wlx.ojbackendmodel.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("content")
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private Integer type;

    private String content;

    private Long categoryId;

    private Long userId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Date publishTime;
}    