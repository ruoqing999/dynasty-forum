package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-01-28
 */
public interface ICategoryService extends IService<Category> {

    List<CategoryVO> getList(Category category);

}
