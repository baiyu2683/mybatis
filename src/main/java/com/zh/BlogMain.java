package com.zh;

import com.zh.entity.Blog;
import com.zh.mapper.AuthorMapper;
import com.zh.mapper.BlogMapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

public class BlogMain {

    public static void main(String[] args) {
        // 1. 创建sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2. 解析配置文件获得SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(App.class.getResourceAsStream("/mybatis-config.xml"));
        // 3. 通过工厂获得一个SqlSession, 创建事务管理器，执行器，封装为一个session
        SqlSession session = sqlSessionFactory.openSession();
        // 4. 通过SqlSession获得指定Mapper的代理，根据类型获得一个MapperProxy为Invocationhandler的jdk动态代理
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

//        List<Blog> list = blogMapper.selectAll();
//        System.out.println(list.size());
        // 5. 通过指定Mapper的代理执行指定的方法
//        List<Blog> list = blogMapper.selectAllBlogAuthorResultMap();

//        List<Blog> list1 = blogMapper.selectBlogPostsResultMap();

//        List<Blog> list2 = blogMapper.selectBlogPostsResultMap2();

        List<Blog> page = blogMapper.page(2, new RowBounds(0, 10));

        session.commit();
        session.close();
    }
}
