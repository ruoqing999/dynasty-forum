package com.ruoqing.dynastyForum.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadImgService {

    String uploadImg(MultipartFile file) throws IOException;

}
