package com.wlx.contentservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlx.contentservice.service.ContentService;
import com.wlx.ojbackendcommon.common.BaseResponse;
import com.wlx.ojbackendcommon.common.ResultUtils;
import com.wlx.ojbackendmodel.model.entity.Content;
import com.wlx.serviceclient.service.ContentClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Resource
    private ContentService contentService;
    
    @Resource
    private ContentClient contentClient;

    @GetMapping("/{id}")
    public BaseResponse<Content> getById(@PathVariable("id") Long id) {
        Content content = contentService.getContentById(id);
        return ResultUtils.success(content);
    }

    @PostMapping
    public BaseResponse<Long> create(@RequestBody Content content) {
        contentService.addContent(content);
        return ResultUtils.success(content.getId());
    }

    @PutMapping
    public BaseResponse<Boolean> update(@RequestBody Content content) {
        boolean success = contentService.updateContent(content);
        return ResultUtils.success(success);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Boolean> delete(@PathVariable("id") Long id) {
        boolean success = contentService.deleteContent(id);
        return ResultUtils.success(success);
    }

    @GetMapping("/page")
    public BaseResponse<Page<Content>> page(
        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "type", required = false) Integer type,
        @RequestParam(value = "categoryId", required = false) Long categoryId
    ) {
        Page<Content> page = contentService.pageContent(pageNum, pageSize, title, type, categoryId);
        return ResultUtils.success(page);
    }

}    