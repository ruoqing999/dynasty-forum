package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.Category;
import com.ruoqing.dynastyForum.service.ICategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author java
 * @since 2024-01-28
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private ICategoryService categoryService;

    @GetMapping("/list")
    public Result<List<Category>> list(){
        return Result.ok(categoryService.list());
    }

}
