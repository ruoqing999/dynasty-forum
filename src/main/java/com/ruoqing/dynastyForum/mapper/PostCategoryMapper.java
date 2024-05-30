package com.ruoqing.dynastyForum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoqing.dynastyForum.entity.PostCategory;
import com.ruoqing.dynastyForum.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PostCategoryMapper extends BaseMapper<PostCategory> {

    List<CategoryVO> listByPostIds(@Param("postIds") List<Integer> postIds);

    void deleteByPostId(@Param("postId") Integer postId);

}
