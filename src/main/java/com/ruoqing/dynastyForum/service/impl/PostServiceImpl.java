package com.ruoqing.dynastyForum.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.Post;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.mapper.PostMapper;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import com.ruoqing.dynastyForum.vo.PostVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        post.setUserId(UserContext.get().getThirdOauthId());
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
        return baseMapper.detail(postId);
    }

    @Override
    public PageInfo<PostVO> pagePost(PostQO qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<PostVO> postVOS = baseMapper.pagePost(qo);
        return new PageInfo<>(postVOS);
    }

    @Override
    public void addViews(Integer postId) {
        baseMapper.addViews(postId);
    }

    @Override
    public void updateCount(UserPost userPost) {
        baseMapper.updateCount(userPost);
    }

}
