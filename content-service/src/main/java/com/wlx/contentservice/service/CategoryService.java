package com.wlx.contentservice.service;


import com.wlx.ojbackendmodel.model.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 获取所有分类列表
     */
    List<Category> listCategories();

    /**
     * 根据ID获取分类
     */
    Category getCategoryById(Long id);

    /**
     * 添加分类
     */
    void addCategory(Category category);

    /**
     * 更新分类
     *
     * @return
     */
    boolean updateCategory(Category category);

    /**
     * 删除分类
     *
     * @return
     */
    boolean deleteCategory(Long id);

    List<Category> listByIds(List<Long> idList);
}    