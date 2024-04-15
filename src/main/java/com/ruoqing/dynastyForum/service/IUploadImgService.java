package com.ruoqing.dynastyForum.service;


import org.springframework.web.multipart.MultipartFile;



public interface IUploadImgService {

    String uploadImg(MultipartFile file);

}
