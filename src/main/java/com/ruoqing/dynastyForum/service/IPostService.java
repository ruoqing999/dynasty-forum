package com.ruoqing.dynastyForum.service;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.ro.PostRO;
import com.ruoqing.dynastyForum.vo.CountVO;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import com.ruoqing.dynastyForum.vo.PostVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface IPostService extends IService<Post> {

    PageInfo<PostVO> pagePost(PostQO qo);

    void post(MultipartFile file, PostRO postRO) throws IOException;

    void del(Integer postId);

    PostDetailVO detail(Integer postId, boolean isPv);

    void addViews(Integer postId);

    CountVO getPostCount(Integer postId, Integer userId);

}
