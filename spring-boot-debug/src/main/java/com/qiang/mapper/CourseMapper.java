package com.qiang.mapper;

import com.qiang.po.CoursePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liq
 * @date 2022/1/21 18:12
 */
@Mapper
//@CacheNamespace(blocking = true)
public interface CourseMapper {
    CoursePo selectById(Long id);

    // 测试重载方法, 不可以重载, 因为是根据mapperInterface名+methodName寻找mapperStatement的
    CoursePo selectById(@Param("id") Long id, @Param("courseName") String courseName);

    List<CoursePo> selectByName(String courseName);

    int updateById(@Param("id") long id);
}
