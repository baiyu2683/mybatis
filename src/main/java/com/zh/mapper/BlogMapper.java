package com.zh.mapper;

import com.zh.entity.Blog;

import java.util.List;

public interface BlogMapper {

    List<Blog> selectAll();
    List<Blog> selectAllBlogAuthorResultMap();
    List<Blog> selectBlogPostsResultMap();
    List<Blog> selectBlogPostsResultMap2();
}
