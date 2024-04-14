package com.ruoqing.dynastyForum.service;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import com.ruoqing.dynastyForum.vo.PostVO;

import java.util.List;

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

    PageInfo<PostVO> pagePost(PostQO qo);

    void addViews(Integer postId);

}
