package com.qiang.mapper;

import com.qiang.entity.Blog;

/**
 * @author liq
 * @date 2021/5/20 18:03
 */
public interface BlogMapper {
    Blog selectBlog(int id);

    int updateBlog(Blog blog);
}
