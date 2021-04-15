package com.zh.mapper;

import com.zh.entity.Author;

import java.util.List;

public interface AuthorMapper {

    List<Author> selectAll();

//    Author selectById(long id);

    long insert(Author author);

    void deleteById(long id);

}
