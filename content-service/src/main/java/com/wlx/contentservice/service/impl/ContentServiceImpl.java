package com.wlx.contentservice.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlx.contentservice.mapper.ContentMapper;
import com.wlx.contentservice.service.ContentService;
import com.wlx.ojbackendmodel.model.entity.Content;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Override
    public Page<Content> listContent(Integer pageNum, Integer pageSize, String title, Integer type, Integer status) {
        Page<Content> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Content> queryWrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(title)) {
            queryWrapper.like(Content::getTitle, title);
        }
        if (type != null) {
            queryWrapper.eq(Content::getType, type);
        }
        if (status != null) {
            queryWrapper.eq(Content::getStatus, status);
        }
        
        queryWrapper.orderByDesc(Content::getCreateTime);
        return page(page, queryWrapper);
    }

    @Override
    public Content getContentById(Long id) {
        return getById(id);
    }

    @Override
    public void addContent(Content content) {
        // 调用MyBatis-Plus的save方法保存内容
        save(content);
    }


    @Override
    public boolean updateContent(Content content) {
        return updateById(content);
    }

    @Override
    public boolean deleteContent(Long id) {
        return removeById(id);
    }



    @Override
    public List<Content> listByIds(List<Long> idList) {
        return listByIds(idList);
    }

    @Override
    public Page<Content> pageContent(Integer pageNum, Integer pageSize, String title, Integer type, Long categoryId) {
        // 创建分页对象
        Page<Content> page = new Page<>(pageNum, pageSize);

        // 构建动态查询条件
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();

        // 添加标题模糊查询
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("title", title);
        }

        // 添加内容类型查询
        if (type != null) {
            queryWrapper.eq("type", type);
        }

        // 添加分类ID查询
        if (categoryId != null) {
            queryWrapper.eq("category_id", categoryId);
        }

        // 设置默认排序（按创建时间降序）
        queryWrapper.orderByDesc("create_time");

        // 执行分页查询
        return page(page, queryWrapper);
    }

}