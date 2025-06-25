package com.wlx.contentservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlx.ojbackendmodel.model.entity.Content;

import java.util.List;

public interface ContentService {

    /**
     * 分页查询内容列表
     */
    Page<Content> listContent(Integer pageNum, Integer pageSize, String title, Integer type, Integer status);

    /**
     * 根据ID获取内容
     */
    Content getContentById(Long id);

    /**
     * 添加内容
     */
    void addContent(Content content);

    /**
     * 更新内容
     *
     * @return
     */
    boolean updateContent(Content content);

    /**
     * 删除内容
     *
     * @return
     */
    boolean deleteContent(Long id);

/*
    Page<Content> pageContent(PageRequest pageRequest, Map<String, Object> params);*/

    List<Content> listByIds(List<Long> idList);

    Page<Content> pageContent(Integer pageNum, Integer pageSize, String title, Integer type, Long categoryId);
}