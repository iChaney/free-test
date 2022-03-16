package com.qiang.service.impl;

import com.qiang.mapper.StoragePOMapper;
import com.qiang.pojo.RestResponse;
import com.qiang.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liq
 * @date 2022/2/16 13:59
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StoragePOMapper storagePOMapper;

    @Override
    public RestResponse reduce(String commodityCode, Integer count) {
        storagePOMapper.updateCount(commodityCode, count);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RestResponse.success();
    }
}
