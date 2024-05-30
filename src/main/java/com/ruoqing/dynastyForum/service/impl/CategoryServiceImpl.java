package com.ruoqing.dynastyForum.service.impl;

import com.ruoqing.dynastyForum.entity.Category;
import com.ruoqing.dynastyForum.entity.PostCategory;
import com.ruoqing.dynastyForum.mapper.CategoryMapper;
import com.ruoqing.dynastyForum.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.service.IPostCategoryService;
import com.ruoqing.dynastyForum.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private IPostCategoryService postCategoryService;

    @Override
    public List<CategoryVO> getList(Category category) {
        String categoryName = category.getCategoryName();
        Integer categoryId = category.getCategoryId();
        List<Category> categories = lambdaQuery()
                .eq(StringUtils.hasText(categoryName), Category::getCategoryName, categoryName)
                .eq(categoryId != null, Category::getCategoryId, categoryId)
                .list();

        if (CollectionUtils.isEmpty(categories)) {
            return Collections.emptyList();
        }

        List<Integer> categoryIds = categories.stream().map(Category::getCategoryId).collect(Collectors.toList());
        Map<Integer, List<PostCategory>> categoryId2Map = postCategoryService.lambdaQuery().in(PostCategory::getCategoryId, categoryIds)
                .list().stream().collect(Collectors.groupingBy(PostCategory::getCategoryId));

        return categories.stream().map(x -> {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(x, categoryVO);
            List<PostCategory> postCategories = categoryId2Map.get(x.getCategoryId());
            categoryVO.setPostCount(CollectionUtils.isEmpty(postCategories) ? 0 : postCategories.size());
            return categoryVO;
        }).toList();
    }
}
