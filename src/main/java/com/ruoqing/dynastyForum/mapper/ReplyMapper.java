package com.ruoqing.dynastyForum.mapper;

import com.ruoqing.dynastyForum.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoqing.dynastyForum.vo.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface ReplyMapper extends BaseMapper<Reply> {

    List<ReplyVO> listReply(@Param("commentIds") Set<Integer> commentIdSets);

}
