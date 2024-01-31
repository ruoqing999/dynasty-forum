package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.LocalAuth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface ILocalAuthService extends IService<LocalAuth> {

    boolean registerUser(Integer userId, String account, String password);

}
