package com.wlx.contentservice.controller.inner;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlx.contentservice.service.CategoryService;
import com.wlx.contentservice.service.ContentService;
import com.wlx.ojbackendmodel.model.entity.Category;
import com.wlx.ojbackendmodel.model.entity.Content;
import com.wlx.serviceclient.service.ContentClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/inner")
public class ContentInnerController implements ContentClient {

    @Resource
    private ContentService contentService;
    @Resource
    private CategoryService categoryService;

    @Override
    @GetMapping("/Content/{id}")
    public Content getById(@PathVariable("id") Long id) {
        return contentService.getContentById(id);
    }

    @Override
    @PostMapping("/Content/list")
    public List<Content> listByIds(@RequestBody List<Long> idList) {
        return contentService.listByIds(idList);
    }

    @Override
    @GetMapping("/Content/page")
    public Page<Content> pageContent(
        @RequestParam("pageNum") Integer pageNum,
        @RequestParam("pageSize") Integer pageSize,
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "type", required = false) Integer type,
        @RequestParam(value = "categoryId", required = false) Long categoryId
    ) {
        return contentService.pageContent(pageNum, pageSize, title, type, categoryId);
    }

    @Override
    @GetMapping("/Category/{id}")
    public Category CagetById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @Override
    @PostMapping("/Category/list")
    public List<Category> CalistByIds(@RequestBody List<Long> idList) {
        return categoryService.listByIds(idList);
    }

    @Override
    @GetMapping("/Category/all")
    public List<Category> CalistAll() {
        return categoryService.listCategories();
    }
}    