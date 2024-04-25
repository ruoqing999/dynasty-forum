package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.Comment;
import com.ruoqing.dynastyForum.entity.Reply;
import com.ruoqing.dynastyForum.mapper.CommentMapper;
import com.ruoqing.dynastyForum.ro.CommentRO;
import com.ruoqing.dynastyForum.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.service.IPostService;
import com.ruoqing.dynastyForum.service.IReplyService;
import com.ruoqing.dynastyForum.service.IUserPostService;
import com.ruoqing.dynastyForum.vo.CommentVO;
import com.ruoqing.dynastyForum.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Resource
    private IReplyService replyService;

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
//        comment.setUserId(UserContext.get().getUserId());
        save(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(Integer commentId) {
        lambdaUpdate().eq(Comment::getCommentId, commentId)
                .set(Comment::getStatus, Whether.F)
                .update();

//        postService.updateCount();

    }

    @Override
    public List<CommentVO> listComment(Integer postId) {
        List<CommentVO> commentVOS = baseMapper.listComment(postId);
        Set<Integer> commentIdSets = commentVOS.stream().map(CommentVO::getCommentId).collect(Collectors.toSet());
        Map<Integer, List<ReplyVO>> commentId2Reply = replyService.listReply(commentIdSets)
                .stream()
                .collect(Collectors.groupingBy(ReplyVO::getCommentId));
        commentVOS.forEach(x -> x.setReplyVOList(commentId2Reply.get(x.getCommentId())));
        return commentVOS;
    }
}
