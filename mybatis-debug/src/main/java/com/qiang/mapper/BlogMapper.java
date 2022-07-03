package com.qiang.mapper;

import com.qiang.entity.Blog;
import org.apache.ibatis.annotations.Param;

/**
 * @author liq
 * @date 2021/5/20 18:03
 */
public interface BlogMapper {
    int updateBlog(Blog blog);
    // 同名方法可以重载
    Blog selectBlog(@Param("tableName") String tableName, @Param("id") int id);
}
