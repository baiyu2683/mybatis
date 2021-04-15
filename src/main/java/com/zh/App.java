package com.zh;

import com.zh.entity.Author;
import com.zh.mapper.AuthorMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(App.class.getResourceAsStream("/mybatis-config.xml"));
        SqlSession session = sqlSessionFactory.openSession();
        AuthorMapper authorMapper = session.getMapper(AuthorMapper.class);

//        Author author = new Author();
//        author.setUsername("myName");
//        author.setPassword("1234");
//        author.setEmail("123@gmail.com");
//        author.setBio("hhahahah");
//
//        long id =authorMapper.insert(author);
//
//        System.out.println(author.getId());
//        session.commit();

        List<Author> authorList = authorMapper.selectAll();

        session.close();
    }
}
