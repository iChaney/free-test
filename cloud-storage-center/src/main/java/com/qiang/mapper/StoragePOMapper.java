package com.qiang.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author liq
 * @date 2022/2/16 14:04
 */
public interface StoragePOMapper {
    int updateCount(@Param("commodityCode") String commodityCode, @Param("count") Integer count);
}
