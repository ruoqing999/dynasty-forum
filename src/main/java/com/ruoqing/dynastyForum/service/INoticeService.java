package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.vo.NoticeDetailVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-04-09
 */
public interface INoticeService extends IService<Notice> {

    NoticeDetailVO detail(Integer noticeId);

}
