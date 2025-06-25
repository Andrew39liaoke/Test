-- 创建库
create database if not exists wlx;

-- 切换库
use wlx;

CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `username` varchar(50) NOT NULL COMMENT '用户名',
                        `password` varchar(100) NOT NULL COMMENT '密码',
                        `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                        `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
                        `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `idx_username` (`username`),
                        UNIQUE KEY `idx_email` (`email`),
                        UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- 表结构内容分类表
-- ----------------------------
CREATE TABLE `category` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
                            `name` varchar(50) NOT NULL COMMENT '分类名称',
                            `parent_id` bigint(20) DEFAULT 0 COMMENT '父分类ID',
                            `sort` int(11) DEFAULT 0 COMMENT '排序',
                            `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            KEY `idx_parent_id` (`parent_id`),
                            KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容分类表';

-- ----------------------------
-- 表结构内容表
-- ----------------------------
CREATE TABLE `content` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '内容ID',
                           `title` varchar(100) NOT NULL COMMENT '标题',
                           `type` tinyint(4) NOT NULL COMMENT '内容类型：1-文章，2-图片，3-视频',
                           `content` text COMMENT '内容',
                           `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
                           `user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
                           `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-草稿，1-发布，2-下架',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
                           PRIMARY KEY (`id`),
                           KEY `idx_title` (`title`),
                           KEY `idx_type` (`type`),
                           KEY `idx_category_id` (`category_id`),
                           KEY `idx_user_id` (`user_id`),
                           KEY `idx_status` (`status`),
                           KEY `idx_publish_time` (`publish_time`),
                           FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL,
                           FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表';