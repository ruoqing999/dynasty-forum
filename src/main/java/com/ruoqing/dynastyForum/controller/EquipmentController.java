package com.ruoqing.dynastyForum.controller;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.PageQO;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.Equipment;
import com.ruoqing.dynastyForum.service.IEquipmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author java
 * @since 2024-06-05
 */
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Resource
    private IEquipmentService equipmentService;

    @IgnoreAuth
    @GetMapping("pageEquipment")
    public Result<PageInfo<Equipment>> pageEquipment(PageQO qo) {
        return Result.ok(equipmentService.pageEquipment(qo));
    }

}
