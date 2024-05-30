package com.ruoqing.dynastyForum.util;

import com.ruoqing.dynastyForum.handler.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
public class FileUtil {

    /**
     * 文件是否过大
     */
    public static boolean isFileNotTooBig(byte[] bytes) {
        //1024*1024*5=5M
        return bytes.length > 5242880L;
    }

    public static File transferToFile(MultipartFile multipartFile) {
        File file;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            Assert.isTrue(originalFilename == null, "文件不能为空");
            //获取文件后缀
            String prefix = originalFilename.substring(originalFilename.lastIndexOf("."));
            file = File.createTempFile(originalFilename, prefix);
            multipartFile.transferTo(file);
            //删除
            file.deleteOnExit();
        } catch (IOException e) {
            log.error("multipartFile to File Error ", e);
            throw new ServiceException(e.getMessage());
        }
        return file;
    }

}
