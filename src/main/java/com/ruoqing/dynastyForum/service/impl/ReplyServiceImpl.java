package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.Reply;
import com.ruoqing.dynastyForum.mapper.ReplyMapper;
import com.ruoqing.dynastyForum.ro.ReplyRO;
import com.ruoqing.dynastyForum.service.IReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.vo.ReplyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    @Override
    public void reply(ReplyRO ro) {
        var replyId = ro.getReplyId();
        Reply reply = new Reply();
        BeanUtils.copyProperties(ro, reply);
        if (null != replyId) {
            //update
            updateById(reply);
            return;
        }
//        reply.setUserId(UserContext.get().getUserId());
        save(reply);
    }

    @Override
    public void del(Integer replyId) {
        lambdaUpdate().eq(Reply::getReplyId, replyId)
                .set(Reply::getStatus, Whether.F)
                .update();
    }

    @Override
    public List<ReplyVO> listReply(Set<Integer> commentIdSets) {
       return baseMapper.listReply(commentIdSets);
    }
}
