package com.qiang.vo;

import com.qiang.po.CoursePo;
import lombok.Data;

import java.util.List;

/**
 * @author liq
 * @date 2022/2/9 17:18
 */
@Data
public class CourseListVO {
    // list
    private List<CoursePo> list;
    // 总数
    private long total;
}
