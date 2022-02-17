package com.qiang.service;

import com.qiang.pojo.RestResponse;

/**
 * @author liq
 * @date 2022/2/16 13:59
 */
public interface StorageService {
    RestResponse reduce(String commodityCode, Integer count);
}
