package com.wlx.contentservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlx.contentservice.mapper.CategoryMapper;
import com.wlx.contentservice.service.CategoryService;
import com.wlx.ojbackendmodel.model.entity.Category;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> listCategories() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        return list(queryWrapper);
    }

    @Override
    public Category getCategoryById(Long id) {
        return getById(id);
    }

    @Override
    public void addCategory(Category category) {
        // 设置默认排序值
        if (category.getSort() == null) {
            category.setSort(0);
        }
        // 设置默认状态为启用
        if (category.getStatus() == null) {
            category.setStatus(1);
        }
        // 设置创建时间和更新时间
        Date now = new Date();
        category.setCreateTime(now);
        category.setUpdateTime(now);
        // 调用MyBatis-Plus的save方法保存分类
        save(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        updateById(category);
        return false;
    }

    @Override
    public boolean deleteCategory(Long id) {
        boolean b = removeById(id);
        return b;
    }


    @Override
    public List<Category> listByIds(List<Long> idList) {
// 检查ID列表是否为空
        if (idList == null || idList.isEmpty()) {
            return Collections.emptyList();
        }
        return listByIds(idList);
    }
}