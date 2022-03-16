package com.qiang.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liq
 * @date 2022/1/21 18:12
 */
@Data
public class CoursePo {
    private Long id;
    private String courseName;
    private String courseType;
    private LocalDateTime startTime;
}
