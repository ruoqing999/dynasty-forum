package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.Notice;
import com.ruoqing.dynastyForum.service.INoticeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author java
 * @since 2024-04-09
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private INoticeService noticeService;

    @IgnoreAuth
    @GetMapping("/list")
    public Result<List<Notice>> list() {
        return Result.ok(noticeService.lambdaQuery().orderByDesc(Notice::getUpdateTime).last("limit 6").list());
    }


    @IgnoreAuth
    @GetMapping("/get")
    public Result<Notice> get(@RequestParam Integer noticeId) {
        return Result.ok(noticeService.lambdaQuery().eq(Notice::getNoticeId, noticeId).one());
    }

}
