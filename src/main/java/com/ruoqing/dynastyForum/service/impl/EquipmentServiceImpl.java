package com.ruoqing.dynastyForum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoqing.dynastyForum.common.PageQO;
import com.ruoqing.dynastyForum.entity.Equipment;
import com.ruoqing.dynastyForum.mapper.EquipmentMapper;
import com.ruoqing.dynastyForum.service.IEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author java
 * @since 2024-06-05
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {

    @Resource
    private EquipmentMapper equipmentMapper;

    @Override
    public PageInfo<Equipment> pageEquipment(PageQO qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<Equipment> list = equipmentMapper.pageEquipment(qo);
        return new PageInfo<>(list);
    }
}
