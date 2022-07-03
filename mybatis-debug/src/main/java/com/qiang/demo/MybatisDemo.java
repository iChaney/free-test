package com.qiang.demo;

import com.qiang.entity.Blog;
import com.qiang.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis的xml版使用
 *
 * @author liq
 * @date 2021/5/20 15:26
 */
public class MybatisDemo {
    @Resource
    TransactionTemplate transactionTemplate;

    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog("blog", 1);
        System.out.println(blog);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        BlogMapper mapper2 = sqlSession2.getMapper(BlogMapper.class);
        Blog blog2 = mapper2.selectBlog("blog", 1);
        System.out.println(blog2);
    }

}
