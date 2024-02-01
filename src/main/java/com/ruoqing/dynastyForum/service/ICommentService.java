package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.ro.CommentRO;

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

}
