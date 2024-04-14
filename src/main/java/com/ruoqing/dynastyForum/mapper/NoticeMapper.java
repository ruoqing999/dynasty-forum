package com.ruoqing.dynastyForum.mapper;

import com.ruoqing.dynastyForum.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoqing.dynastyForum.vo.NoticeDetailVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author java
 * @since 2024-04-09
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    NoticeDetailVO detail(@Param("noticeId") Integer noticeId);

}
