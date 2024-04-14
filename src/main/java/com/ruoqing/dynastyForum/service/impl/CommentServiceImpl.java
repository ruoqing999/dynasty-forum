package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.Comment;
import com.ruoqing.dynastyForum.mapper.CommentMapper;
import com.ruoqing.dynastyForum.ro.CommentRO;
import com.ruoqing.dynastyForum.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public void comment(CommentRO ro) {
        var commentId = ro.getCommentId();
        Comment comment = new Comment();
        BeanUtils.copyProperties(ro, comment);
        if (null != commentId) {
            //update
            updateById(comment);
            return;
        }
        comment.setUserId(UserContext.get().getUserId());
        save(comment);
    }

    @Override
    public void del(Integer commentId) {
        lambdaUpdate().eq(Comment::getCommentId, commentId)
                .set(Comment::getStatus, Whether.F)
                .update();
    }
}
