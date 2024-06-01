package com.ruoqing.dynastyForum.controller;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.service.IUserPostService;
import com.ruoqing.dynastyForum.vo.PostVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author java
 * @since 2024-04-23
 */
@RestController
@RequestMapping("/userPost")
public class UserPostController {

    @Resource
    private IUserPostService userPostService;

    @PostMapping
    public Result<Void> post(@RequestBody UserPost userPost) {
        userPostService.post(userPost);
        return Result.ok();
    }

    @IgnoreAuth
    @GetMapping("/listApprovePost")
    public Result<PageInfo<PostVO>> listApprovePost(PostQO qo) {
        return Result.ok(userPostService.listApprovePost(qo));
    }

}
