package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.mapper.UserMapper;
import com.ruoqing.dynastyForum.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
