package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.ThirdOauth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.vo.QQUserInfO;
import com.ruoqing.dynastyForum.vo.UserInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface IThirdOauthService extends IService<ThirdOauth> {

    String qqUrl();

    UserInfoVO qqLogin(String code, String state);

}
