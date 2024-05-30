package com.ruoqing.dynastyForum.controller;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.service.IUploadImgService;
import com.ruoqing.dynastyForum.service.IUserService;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.util.FileUtil;
import com.ruoqing.dynastyForum.vo.FollowCountVO;
import com.ruoqing.dynastyForum.vo.PostVO;
import com.ruoqing.dynastyForum.vo.UserOperateCountVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUploadImgService uploadImgService;

    @Resource
    private IPostService postService;

    @Resource
    private IUserService userService;


    @PostMapping("/uploadImg")
    public Result<String> uploadImg(MultipartFile file) {
        return Result.ok(uploadImgService.uploadImg(file));
    }

    @IgnoreAuth
    @GetMapping("/getHotAuthorsList")
    public Result<PageInfo<User>> getHotAuthorsList() {
        return Result.ok(new PageInfo<>(Collections.singletonList(new User())));
    }

    @GetMapping("/getFollowCount")
    public Result<FollowCountVO> getFollowCount(Integer userId) {
        return Result.ok(new FollowCountVO());
    }

    @GetMapping("getUserOperateCount")
    public Result<UserOperateCountVO> getUserOperateCount() {
        return Result.ok(userService.getUserOperateCount());
    }

    @GetMapping("/pagePost")
    public Result<PageInfo<PostVO>> pagePost(PostQO qo) {
        qo.setUserId(UserContext.get().getUserId());
        return Result.ok(postService.pagePost(qo));
    }

    @GetMapping("/isValidUser")
    public Result<Boolean> isValidUser(@RequestParam String username) {
        return Result.ok(userService.isValidUser(username));
    }

    @PutMapping
    public Result<Boolean> put(@RequestBody User user) {
        return Result.ok(userService.put(user));
    }

    @PostMapping("/updateAvatar")
    public Result<Void> updateAvatar(@RequestParam("picture") MultipartFile file) throws IOException {
        Assert.isTrue(FileUtil.isFileNotTooBig(file.getBytes()), "图片超过5m");
        String avatar = uploadImgService.uploadImg(file);
        userService.lambdaUpdate().eq(User::getUserId, UserContext.get().getUserId())
                .set(User::getAvatar, avatar)
                .update();
        return Result.ok();
    }
}
