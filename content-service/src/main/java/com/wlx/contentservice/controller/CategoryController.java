package com.wlx.contentservice.controller;

import com.wlx.contentservice.service.CategoryService;
import com.wlx.ojbackendcommon.common.BaseResponse;
import com.wlx.ojbackendcommon.common.ResultUtils;
import com.wlx.ojbackendmodel.model.entity.Category;
import com.wlx.serviceclient.service.ContentClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ContentClient categoryClient;

    @GetMapping("/{id}")
    public BaseResponse<Category> getById(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResultUtils.success(category);
    }

    @PostMapping
    public BaseResponse<Long> create(@RequestBody Category category) {
        System.out.println("category = " + category.getName());
        categoryService.addCategory(category);
        return ResultUtils.success(category.getId());
    }

    @PutMapping
    public BaseResponse<Boolean> update(@RequestBody Category category) {
        boolean success = categoryService.updateCategory(category);
        return ResultUtils.success(success);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Boolean> delete(@PathVariable("id") Long id) {
        boolean success = categoryService.deleteCategory(id);
        return ResultUtils.success(success);
    }

    @GetMapping("/all")
    public BaseResponse<List<Category>> listAll() {
        List<Category> list = categoryService.listCategories();
        return ResultUtils.success(list);
    }

}    