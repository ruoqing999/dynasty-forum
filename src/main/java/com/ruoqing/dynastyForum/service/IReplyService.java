package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.ro.ReplyRO;
import com.ruoqing.dynastyForum.vo.ReplyVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface IReplyService extends IService<Reply> {

    void reply(ReplyRO ro);

    void del(Integer replyId);

    List<ReplyVO> listReply(Set<Integer> commentIdSets);

}
