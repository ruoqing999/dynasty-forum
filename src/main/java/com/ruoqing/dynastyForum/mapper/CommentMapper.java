package com.ruoqing.dynastyForum.mapper;

import com.ruoqing.dynastyForum.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoqing.dynastyForum.vo.CommentVO;
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
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVO> listComment(@Param("postId") Integer postId);

}
