package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.Post;
import com.ruoqing.dynastyForum.mapper.PostMapper;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Override
    public void post(PostRO postRO) {
        var postId = postRO.getPostId();
        Post post = new Post();
        BeanUtils.copyProperties(postRO, post);
        if (null != postId) {
            //update
            updateById(post);
            return;
        }
        post.setUserId(UserContext.get().getUserId());
        save(post);
    }

    @Override
    public void del(Integer postId) {
        lambdaUpdate().eq(Post::getPostId, postId)
                .set(Post::getStatus, Whether.F)
                .update();
    }

    @Override
    public PostDetailVO detail(Integer postId) {

        return null;
    }

}
