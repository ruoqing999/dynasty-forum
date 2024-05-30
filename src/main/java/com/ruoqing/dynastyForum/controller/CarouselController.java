package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.entity.Carousel;
import com.ruoqing.dynastyForum.service.ICarouselService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author java
 * @since 2024-05-24
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Resource
    private ICarouselService carouselService;

    @IgnoreAuth
    @GetMapping("list")
    public Result<List<Carousel>> list(){
        return Result.ok(carouselService.lambdaQuery().orderByAsc(Carousel::getSort).list());
    }

}
