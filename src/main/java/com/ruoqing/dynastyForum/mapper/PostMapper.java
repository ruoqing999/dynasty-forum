package com.ruoqing.dynastyForum.mapper;

import com.ruoqing.dynastyForum.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoqing.dynastyForum.entity.UserPost;
import com.ruoqing.dynastyForum.qo.PostQO;
import com.ruoqing.dynastyForum.vo.PostDetailVO;
import com.ruoqing.dynastyForum.vo.PostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface PostMapper extends BaseMapper<Post> {

    List<PostVO> pagePost(@Param("qo") PostQO qo);

    void addViews(@Param("postId") Integer postId);

    PostDetailVO detail(@Param("postId") Integer postId);

}
