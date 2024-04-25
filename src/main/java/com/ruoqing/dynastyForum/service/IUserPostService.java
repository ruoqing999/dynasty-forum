package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.UserPost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-04-23
 */
public interface IUserPostService extends IService<UserPost> {

    void post(UserPost userPost);

}
