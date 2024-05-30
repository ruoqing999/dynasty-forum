package com.ruoqing.dynastyForum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.entity.PostCategory;
import com.ruoqing.dynastyForum.mapper.PostCategoryMapper;
import com.ruoqing.dynastyForum.service.IPostCategoryService;
import com.ruoqing.dynastyForum.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IPostCategoryServiceImpl extends ServiceImpl<PostCategoryMapper, PostCategory> implements IPostCategoryService {

    @Resource
    private PostCategoryMapper postCategoryMapper;

    @Override
    public List<CategoryVO> listByPostIds(List<Integer> postIds) {
        return postCategoryMapper.listByPostIds(postIds);
    }

    @Override
    public void deleteByPostId(Integer postId) {
        postCategoryMapper.deleteByPostId(postId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchSave(List<Integer> categoryIds, Integer postId) {
        List<PostCategory> saveList = categoryIds.stream().map(x -> {
            PostCategory postCategory = new PostCategory();
            postCategory.setPostId(postId);
            postCategory.setCategoryId(x);
            return postCategory;
        }).toList();
        saveBatch(saveList);
    }
}
