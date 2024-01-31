package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.entity.Post;
import com.ruoqing.dynastyForum.mapper.PostMapper;
import com.ruoqing.dynastyForum.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
