package com.mybatis;

import com.mybatis.demo.dao.BookDao;
import com.mybatis.demo.entity.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: shipotian
 * @Date: 2020/2/21 21:07
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private BookDao userDao;
    @Before//测试前执行
    public void init()throws Exception{
        //1.读取配置文件
        in= Resources.getResourceAsStream("sqlconfig.xml");
        //2.创建SQLSessionFactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //3.使用工厂生产sqlsession对象
       sqlSession= factory.openSession();
        //4.使用SQLSession创建dao接口的代理对象
      userDao= sqlSession.getMapper(BookDao.class);
    }
    @After//用于执行方法后
    public void destroy()throws Exception{
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }
    //查询所有
    @Test
    public void findAll(){
        //5.使用代理对象执行方法
        List<Book> books=userDao.findAll();
        for (Book book:books){
            System.out.println(book);
        }
    }

    //查询一条数据
    @Test
    public void findBook(){
        Book book=userDao.findById(115);
        System.out.println(book);
    }

    //模糊查询
    @Test
    public void findByName(){
       List<Book> books=userDao.findByName("金");
       for (Book book:books){
           System.out.println(book);
       }
    }
    //添加
    @Test
    public void insertBook(){
        Book book=new Book();
        book.setName("早报");
        book.setAuthorName("李四");
        book.setPrice(55);
        book.setPress("湖北出版社");
        userDao.insertBook(book);
    }
    //修改
    @Test
    public void updateBook(){
        Book book=new Book();
        book.setId(115);
        book.setName("早z报");
        book.setAuthorName("李yi四");
        book.setPrice(55);
        book.setPress("湖北出版社");
        userDao.updateBook(book);
    }
    //删除
    @Test
    public void deleteBook(){
        userDao.deleteBook(115);
    }

}
