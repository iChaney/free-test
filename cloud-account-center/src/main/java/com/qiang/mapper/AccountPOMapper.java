package com.qiang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liq
 * @date 2022/2/16 13:51
 */
@Mapper
public interface AccountPOMapper {
    int updateMoney(@Param("userId") String userId, @Param("money") Integer money);
}
