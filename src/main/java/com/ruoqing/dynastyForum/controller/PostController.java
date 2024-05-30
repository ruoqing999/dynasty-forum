package com.ruoqing.dynastyForum.controller;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.entity.Post;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.vo.CountVO;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import com.ruoqing.dynastyForum.vo.PostVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Slf4j
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

    @PostMapping
    public Result<Void> post(@RequestParam(value = "file", required = false) MultipartFile file, PostRO postRO) throws IOException {
        postService.post(file, postRO);
        return Result.ok();
    }

    @DeleteMapping
    public Result<Void> del(@RequestParam Integer postId) {
        postService.del(postId);
        return Result.ok();
    }

    @IgnoreAuth
    @GetMapping
    public Result<PostDetailVO> detail(@RequestParam Integer postId, @RequestParam(required = false) boolean isPv) {
        return Result.ok(postService.detail(postId, isPv));
    }

    @PostMapping("/addViews")
    public Result<Void> addViews(@RequestBody PostRO postRO) {
        postService.addViews(postRO.getPostId());
        return Result.ok();
    }

    @IgnoreAuth
    @GetMapping("getPostCount")
    public Result<CountVO> getPostCount(@RequestParam(required = false) Integer postId,
                                        @RequestParam(required = false) Integer userId) {
        return Result.ok(postService.getPostCount(postId, userId));
    }

    @PutMapping
    public Result<Void> put(@RequestBody Post post){
        postService.lambdaUpdate()
                .eq(Post::getPostId, post.getPostId())
                .set(post.getTop() != null, Post::getTop, post.getTop())
                .update();
        return Result.ok();
    }


}
