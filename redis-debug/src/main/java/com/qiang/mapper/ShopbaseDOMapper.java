package com.qiang.mapper;

import com.qiang.model.ImportBusinessShopInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopbaseDOMapper {
    List<ImportBusinessShopInfo> importBusinessShopInfoList(@Param("shopId") String shopId);

    List<String> selectHotelImage(@Param("shopId") String shopId);
}
