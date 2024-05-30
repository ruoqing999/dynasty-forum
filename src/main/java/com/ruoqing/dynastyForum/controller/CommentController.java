package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.Comment;
import com.ruoqing.dynastyForum.ro.CommentRO;
import com.ruoqing.dynastyForum.service.ICommentService;
import com.ruoqing.dynastyForum.vo.CommentVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @IgnoreAuth
    @GetMapping("listComment")
    public Result<List<CommentVO>> listComment(@RequestParam Integer postId,
                                               @RequestParam Integer sortType){
        return Result.ok(commentService.listComment(postId, sortType));
    }

}
