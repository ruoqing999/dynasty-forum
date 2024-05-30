package com.ruoqing.dynastyForum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.entity.PostCategory;
import com.ruoqing.dynastyForum.vo.CategoryVO;

import java.util.List;

public interface IPostCategoryService extends IService<PostCategory> {


    List<CategoryVO> listByPostIds(List<Integer> postIds);

    void deleteByPostId(Integer postId);

    void batchSave(List<Integer> categoryIds, Integer postId);

}
