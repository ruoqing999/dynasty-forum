package com.ruoqing.dynastyForum.mapper;

import com.ruoqing.dynastyForum.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoqing.dynastyForum.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author java
 * @since 2024-01-28
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVO> getList(@Param("categoryName") String categoryName);


}
