package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.UserPostBusinessEnum;
import com.ruoqing.dynastyForum.entity.Post;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.mapper.UserMapper;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.service.IUserPostService;
import com.ruoqing.dynastyForum.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import com.ruoqing.dynastyForum.vo.UserOperateCountVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private IPostService postService;

    @Resource
    private IUserPostService userPostService;

    @Override
    public UserOperateCountVO getUserOperateCount(Integer userId) {
        UserOperateCountVO vo = new UserOperateCountVO();

        int postCount = postService.lambdaQuery().eq(Post::getUserId, userId).count().intValue();
        int approveCount = userPostService.lambdaQuery().eq(UserPost::getUserId, userId)
                .eq(UserPost::getType, UserPostBusinessEnum.APPROVE.getCode()).count().intValue();
        vo.setPostCount(postCount);
        vo.setApproveCount(approveCount);
        return vo;
    }

    @Override
    public Boolean isValidUser(String username) {
        UserInfoVO userInfoVO = UserContext.get();
        Integer userId = userInfoVO.getUserId();
        return lambdaQuery().ne(User::getUserId, userId)
                .eq(User::getNickName, username)
                .count().intValue() < 0;
    }

    @Override
    public Boolean put(User user) {
        return lambdaUpdate().eq(User::getUserId, user.getUserId())
                .set(StringUtils.hasText(user.getNickName()), User::getNickName, user.getNickName())
                .set(StringUtils.hasText(user.getIntro()), User::getIntro, user.getIntro())
                .update();
    }

    @Override
    public void updateAvatar(MultipartFile file) {

    }
}
