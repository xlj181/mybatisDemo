package com.mybatis.demo.dao;

import com.mybatis.demo.entity.Book;

import java.util.List;

/**
 * @Author: shipotian
 * @Date: 2020/2/21 20:28
 */
public interface BookDao {
    /**
     * 查询所有
     * @return
     */
    List<Book> findAll();

    /**
     * 查询一条
     * @param id
     * @return
     */
    Book findById(Integer id);

    /**
     * 添加
     * @param book
     */
     void insertBook(Book book);

    /**
     * 修改
     * @param book
     */
    void updateBook(Book book);

    /**
     * 删除
     * @param id
     */
    void deleteBook(Integer id);

    /**
     * 根据作者模糊查询
     * @param authorName
     * @return
     */
    List<Book> findByName(String authorName);
}