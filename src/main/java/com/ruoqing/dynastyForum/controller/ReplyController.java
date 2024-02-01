package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.ro.ReplyRO;
import com.ruoqing.dynastyForum.service.IReplyService;
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
@RequestMapping("/reply")
public class ReplyController {

    @Resource
    private IReplyService replyService;


    @PostMapping
    public Result<Void> reply(@RequestBody @Valid ReplyRO ro) {
        replyService.reply(ro);
        return Result.ok();
    }

    @DeleteMapping
    public Result<Void> del(@RequestParam Integer replyId){
        replyService.del(replyId);
        return Result.ok();
    }

}
