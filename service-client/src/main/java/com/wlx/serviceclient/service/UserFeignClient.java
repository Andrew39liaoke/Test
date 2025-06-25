package com.wlx.serviceclient.service;

import com.wlx.ojbackendmodel.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "user-service", path = "/api/user/inner")
public interface UserFeignClient {

    @GetMapping("/{id}")
    User getById(@PathVariable("id") Long id);

    @PostMapping("/register")
    Long register(@RequestBody User user);

    @PostMapping("/login")
    User login(@RequestBody User user);
}
