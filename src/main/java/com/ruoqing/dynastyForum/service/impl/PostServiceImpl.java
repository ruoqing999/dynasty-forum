package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.UserPostBusinessEnum;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.Comment;
import com.ruoqing.dynastyForum.entity.Post;
import com.ruoqing.dynastyForum.entity.PostCategory;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.mapper.PostMapper;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.util.FileUtil;
import com.ruoqing.dynastyForum.vo.*;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private IPostCategoryService postCategoryService;

    @Resource
    private IUserService userService;

    @Resource
    private ImgTpServiceImpl imgTpService;

    @Resource
    private IUserPostService userPostService;

    @Resource
    private ICommentService commentService;

    @Override
    public PageInfo<PostVO> pagePost(PostQO qo) {
        List<Integer> categoryIds = qo.getCategoryIds();
        if (CollUtil.isNotEmpty(categoryIds)) {
            List<PostCategory> postCategories = postCategoryService.lambdaQuery().in(PostCategory::getCategoryId, categoryIds).list();
            if (CollUtil.isEmpty(postCategories)) {
                return new PageInfo<>(Collections.emptyList());
            }
            qo.setPostIds(postCategories.stream().map(PostCategory::getPostId).toList());
        }
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<PostVO> postVOS = baseMapper.pagePost(qo);
        if (CollUtil.isNotEmpty(postVOS)) {
            List<Integer> postIds = postVOS.stream().map(Post::getPostId).toList();
            Map<Integer, List<CategoryVO>> postId2Map = postCategoryService.listByPostIds(postIds)
                    .stream().collect(Collectors.groupingBy(CategoryVO::getPostId));
            Map<String, Long> postType2Map = userPostService.postType2Map(postIds);
            Map<Integer, List<Comment>> postId2Comment = commentService.lambdaQuery().in(Comment::getPostId, postIds)
                    .list().stream().collect(Collectors.groupingBy(Comment::getPostId));

            UserInfoVO userInfoVO = UserContext.get();
            Map<String, List<UserPost>> userPost2Map;
            if (Objects.nonNull(userInfoVO)) {
                Integer userId = userInfoVO.getUserId();
                userPost2Map = userPostService.lambdaQuery().eq(UserPost::getUserId, userId)
                        .eq(UserPost::getType, UserPostBusinessEnum.APPROVE.getCode())
                        .in(UserPost::getPostId, postIds).list().stream()
                        .collect(Collectors.groupingBy(x -> x.getUserId() + "-" + x.getPostId()));
            } else {
                userPost2Map = new HashMap<>();
            }

            postVOS.forEach(x -> {
                Integer postId = x.getPostId();
                x.setCategoryVOS(postId2Map.get(postId));
                Long approves = postType2Map.get(postId + "-" + UserPostBusinessEnum.APPROVE.getCode());
                x.setApproves(approves == null ? 0 : approves.intValue());
                Long collects = postType2Map.get(postId + "-" + UserPostBusinessEnum.COLLECT.getCode());
                x.setCollections(collects == null ? 0 : collects.intValue());
                List<Comment> comments = postId2Comment.get(postId);
                x.setComments(CollUtil.isEmpty(comments) ? 0 : comments.size());
                if (!CollectionUtils.isEmpty(userPost2Map)) {
                    if (userPost2Map.containsKey(userInfoVO.getUserId() + "-" + postId)) {
                        x.setLike(true);
                    }
                }
            });
        }
        return new PageInfo<>(postVOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void post(MultipartFile file, PostRO postRO) throws IOException {

        var postId = postRO.getPostId();
        Post post = new Post();
        BeanUtils.copyProperties(postRO, post);

        if (file != null) {
            Assert.isTrue(FileUtil.isFileNotTooBig(file.getBytes()), "图片过大，请上传不超过5m的图片");
            String postImg = imgTpService.uploadImg(file);
            post.setPostImg(postImg);
        }

        if (null != postId) {
            //update
            updateById(post);
            postCategoryService.deleteByPostId(postId);
            postCategoryService.batchSave(postRO.getCategoryIds(), postId);
            return;
        }
        post.setUserId(UserContext.get().getUserId());
        save(post);
        postCategoryService.batchSave(postRO.getCategoryIds(), post.getPostId());
    }

    @Override
    public void del(Integer postId) {
        lambdaUpdate().eq(Post::getPostId, postId)
                .set(Post::getStatus, Whether.F)
                .update();
    }

    @Override
    public PostDetailVO detail(Integer postId, boolean isPv) {
        PostDetailVO detail = baseMapper.detail(postId);
        detail.setCategoryVOS(postCategoryService.listByPostIds(Collections.singletonList(postId)));
        if (isPv) {
            addViews(postId);
        }
        return detail;
    }


    @Override
    public void addViews(Integer postId) {
        baseMapper.addViews(postId);
    }

    @Override
    public CountVO getPostCount(Integer postId, Integer userId) {
        CountVO countVO = new CountVO();

        if (null != postId) {
            Post post = getById(postId);
            BeanUtils.copyProperties(post, countVO);
            Map<String, Long> postType2Map = userPostService.postType2Map(Collections.singletonList(postId));
            Long approves = postType2Map.get(postId + "-" + UserPostBusinessEnum.APPROVE.getCode());
            countVO.setApproves(approves == null ? 0 : approves.intValue());
            Long comments = commentService.lambdaQuery().eq(Comment::getPostId, postId).count();
            countVO.setComments(comments == null ? 0 : comments.intValue());
            Long collects = postType2Map.get(postId + "-" + UserPostBusinessEnum.COLLECT.getCode());
            countVO.setCollections(collects == null ? 0 : collects.intValue());
            if (userId != null) {
                List<UserPost> userPostList = userPostService.lambdaQuery()
                        .eq(UserPost::getPostId, postId).eq(UserPost::getUserId, userId).list();
                long approveCount = userPostList.stream().filter(x -> Objects.equals(x.getType(), UserPostBusinessEnum.APPROVE.getCode())).count();
                long collectCount = userPostList.stream().filter(x -> Objects.equals(x.getType(), UserPostBusinessEnum.COLLECT.getCode())).count();
                countVO.setLike(approveCount > 0);
                countVO.setCollect(collectCount > 0);
                countVO.setFollow(false);
            }
        }

        int postCount = lambdaQuery().count().intValue();
        int userCount = userService.lambdaQuery().count().intValue();
        int commentCount = commentService.lambdaQuery().count().intValue();
        countVO.setPostCount(postCount);
        countVO.setUserCount(userCount);
        countVO.setCommentCount(commentCount);
        return countVO;
    }

}
