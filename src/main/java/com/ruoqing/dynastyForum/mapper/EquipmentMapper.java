package com.ruoqing.dynastyForum.mapper;

import com.ruoqing.dynastyForum.common.PageQO;
import com.ruoqing.dynastyForum.entity.Equipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author java
 * @since 2024-06-05
 */
public interface EquipmentMapper extends BaseMapper<Equipment> {

    List<Equipment> pageEquipment(@Param("qo") PageQO qo);

}
