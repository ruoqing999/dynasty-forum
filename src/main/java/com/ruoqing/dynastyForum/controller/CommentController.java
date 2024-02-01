package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.ro.CommentRO;
import com.ruoqing.dynastyForum.service.ICommentService;
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
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @PostMapping
    public Result<Void> comment(@RequestBody @Valid CommentRO ro){
        commentService.comment(ro);
        return Result.ok();
    }

    @DeleteMapping
    public Result<Void> del(@RequestParam Integer commentId){
        commentService.del(commentId);
        return Result.ok();
    }

}
