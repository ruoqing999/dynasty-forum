package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.constant.UserPostBusinessEnum;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.mapper.UserPostMapper;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.service.IUserPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author java
 * @since 2024-04-23
 */
@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements IUserPostService {

    @Resource
    private IPostService postService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void post(UserPost userPost) {
        saveOrUpdate(userPost);
        postService.updateCount(userPost);
    }
}
