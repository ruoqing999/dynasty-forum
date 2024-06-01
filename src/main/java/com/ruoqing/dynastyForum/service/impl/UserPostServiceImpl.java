package com.ruoqing.dynastyForum.service.impl;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.UserPostBusinessEnum;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.mapper.UserPostMapper;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.service.IUserPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.vo.PostVO;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

        UserInfoVO userInfoVO = UserContext.get();
        userPost.setUserId(userInfoVO.getUserId());
        List<UserPost> userPostList = lambdaQuery().eq(UserPost::getUserId, userInfoVO.getUserId())
                .eq(UserPost::getPostId, userPost.getPostId())
                .eq(UserPost::getType, userPost.getType())
                .list();
        if (CollectionUtils.isEmpty(userPostList)) {
            userPost.setStatus(Whether.T);
            save(userPost);
        } else {
            userPost.setStatus(Whether.F);
            lambdaUpdate().eq(UserPost::getUserId, userInfoVO.getUserId())
                    .eq(UserPost::getPostId, userPost.getPostId())
                    .set(UserPost::getStatus, Whether.F)
                    .update();
        }
    }

    @Override
    public Map<String, Long> postType2Map(List<Integer> postId) {
        return lambdaQuery()
                .in(UserPost::getPostId, postId).list()
                .stream().collect(Collectors.groupingBy(x -> x.getPostId() + "-" + x.getType(), Collectors.counting()));
    }

    @Override
    public PageInfo<PostVO> listApprovePost(PostQO qo) {
        List<Integer> postIds = lambdaQuery().eq(UserPost::getUserId, qo.getUserId()).eq(UserPost::getType, UserPostBusinessEnum.APPROVE.getCode())
                .list().stream().map(UserPost::getPostId).toList();

        if (CollectionUtils.isEmpty(postIds)) {
            return new PageInfo<>(Collections.emptyList());
        }

        qo.setUserId(null);
        qo.setPostIds(postIds);
        return postService.pagePost(qo);
    }
}
