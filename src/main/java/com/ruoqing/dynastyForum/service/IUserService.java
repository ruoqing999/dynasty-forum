package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.vo.UserOperateCountVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface IUserService extends IService<User> {

    UserOperateCountVO getUserOperateCount();

    Boolean isValidUser(String username);

    Boolean put(User user);

    void updateAvatar(MultipartFile file);

}
