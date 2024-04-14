package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.entity.Notice;
import com.ruoqing.dynastyForum.mapper.NoticeMapper;
import com.ruoqing.dynastyForum.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.vo.NoticeDetailVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author java
 * @since 2024-04-09
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public NoticeDetailVO detail(Integer noticeId) {
        return baseMapper.detail(noticeId);
    }
}
