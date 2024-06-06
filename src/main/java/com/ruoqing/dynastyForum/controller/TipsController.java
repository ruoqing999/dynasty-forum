package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.Tips;
import com.ruoqing.dynastyForum.service.ITipsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author java
 * @since 2024-06-06
 */
@RestController
@RequestMapping("/tips")
public class TipsController {

    @Resource
    private ITipsService tipsService;

    @IgnoreAuth
    @GetMapping
    public Result<Tips> getTips(@RequestParam(required = false, defaultValue = "1") Integer tipsId) {
        return Result.ok(tipsService.getById(tipsId));
    }

}
