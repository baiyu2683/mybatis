package com.zh.mapper;

import com.zh.entity.Author;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorMapper {

    List<Author> selectAll();

    List<Author> selectAllResultMap();

    List<Author> select(@Param("password") String password, @Param("username") String username);

//    Author selectById(long id);

    long insert(Author author);

    void insertAll(List<Author> authorList);

    void deleteById(long id);

}
