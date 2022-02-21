package com.zh.mapper;

import com.zh.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BlogMapper {

    List<Blog> selectAll();
    List<Blog> selectAllBlogAuthorResultMap();
    List<Blog> selectBlogPostsResultMap();
    List<Blog> selectBlogPostsResultMap2();

    /**
     * 分页
     * @param authorId
     * @param rowBounds
     * @return
     */
    List<Blog> page(@Param("authorId") long authorId, RowBounds rowBounds);
}
