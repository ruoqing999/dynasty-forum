package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private IPostService postService;

    @PostMapping
    public Result<Void> post(@RequestBody @Valid PostRO postRO){
        postService.post(postRO);
        return Result.ok();
    }

    @DeleteMapping
    public Result<Void> del(@RequestParam Integer postId){
        postService.del(postId);
        return Result.ok();
    }

    @GetMapping
    public Result<PostDetailVO> detail(@RequestParam Integer postId){
        return Result.ok(postService.detail(postId));
    }

}
