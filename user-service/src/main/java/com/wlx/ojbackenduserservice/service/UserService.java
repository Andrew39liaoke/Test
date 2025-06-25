package com.wlx.ojbackenduserservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wlx.ojbackendmodel.model.entity.User;

public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param user 用户信息
     * @return 用户ID
     */
    Long register(User user);

    /**
     * 用户登录
     * @param user 用户信息（包含用户名和密码）
     * @return 登录成功的用户信息
     */
    User login(User user);
}