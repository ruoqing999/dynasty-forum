package com.ruoqing.dynastyForum.controller;

import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.service.IUploadImgService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

//    @Resource
    private IUploadImgService uploadImgService;

    @PostMapping("/uploadImg")
    public Result<String> uploadImg(MultipartFile file) throws IOException {
        return Result.ok(uploadImgService.uploadImg(file));
    }


}
