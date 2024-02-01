package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.ro.ReplyRO;

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

}
