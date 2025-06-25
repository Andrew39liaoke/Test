package com.wlx.ojbackenduserservice.controller.inner;

import com.wlx.ojbackendmodel.model.entity.User;
import com.wlx.ojbackenduserservice.service.UserService;
import com.wlx.serviceclient.service.UserFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 该服务仅内部调用，不是给前端的
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;

    @Override
    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @Override
    @PostMapping("/register")
    public Long register(@RequestBody User user) {
        return userService.register(user);
    }

    @Override
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }
}