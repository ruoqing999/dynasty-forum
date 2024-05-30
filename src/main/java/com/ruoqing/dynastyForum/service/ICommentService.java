package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.ro.CommentRO;
import com.ruoqing.dynastyForum.vo.CommentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface ICommentService extends IService<Comment> {

    void comment(CommentRO ro);

    void del(Integer commentId);

    List<CommentVO> listComment(Integer postId, Integer sortType);

}
