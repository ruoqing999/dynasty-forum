package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.SortTypeEnum;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.Comment;
import com.ruoqing.dynastyForum.mapper.CommentMapper;
import com.ruoqing.dynastyForum.ro.CommentRO;
import com.ruoqing.dynastyForum.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.util.CommentTreeUtils;
import com.ruoqing.dynastyForum.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    @Transactional(rollbackFor = Exception.class)
    public void del(Integer commentId) {
        lambdaUpdate().eq(Comment::getCommentId, commentId)
                .set(Comment::getStatus, Whether.F)
                .update();
    }

    @Override
    public List<CommentVO> listComment(Integer postId, Integer sortType) {
        List<CommentVO> commentVOS = baseMapper.listComment(postId);
        if (CollectionUtil.isNotEmpty(commentVOS)) {
            buildChildComment(commentVOS);
        }
        commentVOS = CommentTreeUtils.toTree(commentVOS);
        // 最热评论（先按点赞数降序，再按回复数降序）
        if (SortTypeEnum.HOTTEST.getCode() == sortType) {
            commentVOS = commentVOS.stream()
                    .sorted(Comparator.comparing(CommentVO::getApproves, Comparator.nullsLast(Comparator.reverseOrder()))
                            .thenComparing(CommentVO::getRepliesCount, Comparator.nullsLast(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
        } else if (SortTypeEnum.NEWEST.getCode() == sortType) {
            commentVOS = commentVOS.stream()
                    .sorted(Comparator.comparing(CommentVO::getCreateTime, Comparator.nullsLast(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
        }
        return commentVOS;
    }

    private void buildChildComment(List<CommentVO> commentVOS) {
        Map<Integer, List<CommentVO>> parentId2Map = commentVOS.stream().collect(Collectors.groupingBy(Comment::getParentId));
        commentVOS.forEach(x -> {
            Integer commentId = x.getCommentId();
            if (parentId2Map.containsKey(commentId)) {
                x.setRepliesCount(parentId2Map.get(commentId).size());
            }
        });
    }
}
