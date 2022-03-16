package com.qiang.mapper;

import com.qiang.po.CoursePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liq
 * @date 2022/1/21 18:12
 */
@Mapper
public interface CourseMapper {
    CoursePo selectById(Long id);

    List<CoursePo> selectByName(String courseName);
}
