package com.wlx.ojbackendcommon.common;

import lombok.Data;
import com.wlx.ojbackendcommon.constant.CommonConstant;

/**
 * 分页请求
 *
 * @author <a href="https://github.com/liwlx">程序员鱼皮</a>
 * @from <a href="https://wlx.icu">编程导航知识星球</a>
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
