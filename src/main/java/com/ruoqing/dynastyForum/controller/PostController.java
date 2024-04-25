package com.ruoqing.dynastyForum.controller;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import com.ruoqing.dynastyForum.vo.PostVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
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

    @IgnoreAuth
    @GetMapping("/pagePost")
    public Result<PageInfo<PostVO>> pagePost(PostQO qo) {
        return Result.ok(postService.pagePost(qo));
    }

    @IgnoreAuth
    @PostMapping
    public Result<Void> post(@RequestBody @Valid PostRO postRO) {
        postService.post(postRO);
        return Result.ok();
    }

    @IgnoreAuth
    @DeleteMapping
    public Result<Void> del(@RequestParam Integer postId) {
        postService.del(postId);
        return Result.ok();
    }

    @IgnoreAuth
    @GetMapping
    public Result<PostDetailVO> detail(@RequestParam Integer postId) {
        return Result.ok(postService.detail(postId));
    }

    @IgnoreAuth
    @PostMapping("/addViews")
    public Result<Void> addViews(@RequestBody PostRO postRO) {
        postService.addViews(postRO.getPostId());
        return Result.ok();
    }

    @IgnoreAuth
    @GetMapping("/selectComment")
    public Result<Void> selectComment(){
        return null;
    }

}
