package com.ruoqing.dynastyForum.service;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.vo.PostVO;

import java.util.List;
import java.util.Map;

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

    Map<String, Long> postType2Map(List<Integer> postId);

    PageInfo<PostVO> listApprovePost(PostQO qo);

}
