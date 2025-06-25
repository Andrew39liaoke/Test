package com.wlx.ojbackenduserservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlx.ojbackendcommon.common.ErrorCode;
import com.wlx.ojbackendcommon.exception.BusinessException;
import com.wlx.ojbackendmodel.model.entity.User;
import com.wlx.ojbackenduserservice.mapper.UserMapper;
import com.wlx.ojbackenduserservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final String SALT = "your_salt_here";

    @Override
    public Long register(User user) {
        // 参数校验
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        // 非空校验
        if (username == null || password == null || email == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 用户名长度校验
        if (username.length() < 4 || username.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名长度需在4-20个字符之间");
        }

        // 密码长度校验
        if (password.length() < 6 || password.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度需在6-20个字符之间");
        }

        // 邮箱格式校验（简化）
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱格式不正确");
        }

        // 用户名唯一性校验
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User existingUser = this.getOne(queryWrapper);
        if (existingUser != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名已存在");
        }

        // 邮箱唯一性校验
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        existingUser = this.getOne(queryWrapper);
        if (existingUser != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱已被注册");
        }

        // 手机号唯一性校验（如果有）
        if (user.getPhone() != null) {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, user.getPhone());
            existingUser = this.getOne(queryWrapper);
            if (existingUser != null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号已被注册");
            }
        }

        // 密码加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        user.setPassword(encryptedPassword);

        // 设置默认状态为启用
        user.setStatus(1);

        // 保存用户
        boolean result = this.save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "注册失败");
        }

        return user.getId();
    }

    @Override
    public User login(User user) {
        // 参数校验
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = user.getUsername();
        String password = user.getPassword();

        // 非空校验
        if (username == null || password == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User dbUser = this.getOne(queryWrapper);

        // 用户不存在
        if (dbUser == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }

        // 密码校验
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        if (!Objects.equals(encryptedPassword, dbUser.getPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }

        // 用户状态校验
        if (dbUser.getStatus() == 0) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "用户已被禁用");
        }

        return dbUser;
    }
}