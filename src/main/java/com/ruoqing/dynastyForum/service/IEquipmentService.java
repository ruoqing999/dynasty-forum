package com.ruoqing.dynastyForum.service;

import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.common.PageQO;
import com.ruoqing.dynastyForum.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-06-05
 */
public interface IEquipmentService extends IService<Equipment> {

    PageInfo<Equipment> pageEquipment(PageQO qo);

}
