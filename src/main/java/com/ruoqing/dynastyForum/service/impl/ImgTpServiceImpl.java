package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ruoqing.dynastyForum.component.ImgPTComponent;
import com.ruoqing.dynastyForum.component.PiCuiComponent;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.service.IUploadImgService;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.util.FileUtil;
import com.ruoqing.dynastyForum.vo.ImgPiCuiVO;
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

    @Resource
    private PiCuiComponent piCuiComponent;

    @Override
    public String uploadImg(MultipartFile file) {

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        headerMap.put("Authorization", "Bearer " + piCuiComponent.getToken());
        headerMap.put("Accept", MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("file", FileUtil.transferToFile(file));
        paramMap.put("permission", Whether.NO);

        try (HttpResponse response = HttpUtil.createPost(piCuiComponent.getUrl()).form(paramMap).addHeaders(headerMap).execute()) {
            log.info("上传图片响应: {}", response.body());
            ImgPiCuiVO imgPiCuiVO = JSONUtil.toBean(response.body(), ImgPiCuiVO.class);
            Assert.isTrue(!imgPiCuiVO.getStatus(), "上传失败");
            log.info("imgPiCuiVO: {}", imgPiCuiVO);
            return imgPiCuiVO.getData().getLinks().getUrl();
        }
    }
}
