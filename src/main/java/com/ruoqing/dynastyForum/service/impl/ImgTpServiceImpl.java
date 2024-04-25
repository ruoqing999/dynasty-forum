package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ruoqing.dynastyForum.component.ImgPTComponent;
import com.ruoqing.dynastyForum.service.IUploadImgService;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.util.FileUtil;
import com.ruoqing.dynastyForum.vo.ImgPtVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
public class ImgTpServiceImpl implements IUploadImgService {

    @Resource
    private ImgPTComponent imgPTComponent;

    @Override
    public String uploadImg(MultipartFile file) {

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        headerMap.put("token", imgPTComponent.getToken());

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("image", FileUtil.transferToFile(file));

        try (HttpResponse response = HttpUtil.createPost(imgPTComponent.getUrl()).form(paramMap).addHeaders(headerMap).execute()) {
            log.info("上传图片响应: {}", response.body());
            Assert.isTrue(!Objects.equals(response.getStatus(), HttpStatus.HTTP_OK), "上传失败");
            return JSONUtil.toBean(response.body(), ImgPtVO.class).getData().getUrl();
        }
    }
}
