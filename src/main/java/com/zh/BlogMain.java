package com.zh;

import com.zh.entity.Blog;
import com.zh.mapper.AuthorMapper;
import com.zh.mapper.BlogMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

public class BlogMain {

    public static void main(String[] args) {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(App.class.getResourceAsStream("/mybatis-config.xml"));
        SqlSession session = sqlSessionFactory.openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        List<Blog> list = blogMapper.selectAll();
        System.out.println(list.size());

//        List<Blog> list = blogMapper.selectAllBlogAuthorResultMap();

//        List<Blog> list1 = blogMapper.selectBlogPostsResultMap();

//        List<Blog> list2 = blogMapper.selectBlogPostsResultMap2();


        session.commit();
        session.close();
    }
}
