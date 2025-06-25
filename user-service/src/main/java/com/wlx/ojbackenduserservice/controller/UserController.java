package com.wlx.ojbackenduserservice.controller;

import com.wlx.ojbackendcommon.common.BaseResponse;
import com.wlx.ojbackendcommon.common.ResultUtils;
import com.wlx.ojbackendmodel.model.entity.User;
import com.wlx.ojbackenduserservice.service.UserService;
import com.wlx.serviceclient.service.UserFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserFeignClient userClient;

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody User user) {
        Long userId = userService.register(user);
        return ResultUtils.success(userId);
    }

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody User user) {
        User loginUser = userService.login(user);
        return ResultUtils.success(loginUser);
    }

    @GetMapping("/{id}")
    public BaseResponse<User> getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return ResultUtils.success(user);
    }

}    