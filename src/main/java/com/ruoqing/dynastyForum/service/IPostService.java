package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.vo.PostDetailVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface IPostService extends IService<Post> {

    void post(PostRO postRO);

    void del(Integer postId);

    PostDetailVO detail(Integer postId);

}
