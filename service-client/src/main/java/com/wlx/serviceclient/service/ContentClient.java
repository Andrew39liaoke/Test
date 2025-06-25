package com.wlx.serviceclient.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlx.ojbackendmodel.model.entity.Category;
import com.wlx.ojbackendmodel.model.entity.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "content-service", path = "/api/content/inner")
public interface ContentClient {
    @GetMapping("/Content/{id}")
    Content getById(@PathVariable("id") Long id);

    @PostMapping("/Content/list")
    List<Content> listByIds(@RequestBody List<Long> idList);

    @GetMapping("/Content/page")
    Page<Content> pageContent(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "categoryId", required = false) Long categoryId
    );

    @GetMapping("/Category/{id}")
    Category CagetById(@PathVariable("id") Long id);

    @PostMapping("/Category/list")
    List<Category> CalistByIds(@RequestBody List<Long> idList);

    @GetMapping("/Category/all")
    List<Category> CalistAll();
}
