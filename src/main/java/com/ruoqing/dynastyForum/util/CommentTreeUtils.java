package com.ruoqing.dynastyForum.util;

import com.ruoqing.dynastyForum.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用于树转集合，集合转树场景
 */
@Slf4j
public class CommentTreeUtils {

    /**
     * 集合转树
     */
    public static List<CommentVO> toTree(List<CommentVO> commentVOS) {
        Map<Integer, CommentVO> commentId2Map = commentVOS.stream().collect(Collectors.toMap(CommentVO::getCommentId, e -> e));
        List<CommentVO> root = new ArrayList<>();
        for (CommentVO vo : commentVOS) {
            Integer parentId = vo.getParentId();
            // 是根评论
            if (parentId == 0) {
                // 设置评论深度
                vo.setDepth(0);
                root.add(vo);
            } else {
                CommentVO parent = commentId2Map.get(parentId);
                // 跳过子级无父级的评论
                if (parent == null) {
                    continue;
                }
                List<CommentVO> children = CollectionUtils.isEmpty(parent.getChild()) ? new ArrayList<>() : parent.getChild();
                // 设置评论深度
                vo.setDepth(parent.getDepth() + 1);
                children.add(vo);
                parent.setChild(children);
            }
        }
        return root;
    }


}
