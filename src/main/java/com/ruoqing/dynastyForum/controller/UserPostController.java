package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.service.IUserPostService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
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
    public Result<Void> post(@RequestBody UserPost userPost){
        userPostService.post(userPost);
        return Result.ok();
    }

}
