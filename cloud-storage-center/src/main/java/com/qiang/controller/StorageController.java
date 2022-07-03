package com.qiang.controller;

import com.qiang.pojo.RestResponse;
import com.qiang.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author liq
 * @date 2022/2/16 13:56
 */
@RestController
public class StorageController {
    @Autowired
    StorageService storageService;

    /**
     * 呵呵.呵呵.
     * @param commodityCode
     * @param count
     * @return
     */
    @PostMapping("/reduce")
    RestResponse reduce(@PathParam("commodityCode") String commodityCode, @PathParam("count") Integer count) {
        return storageService.reduce(commodityCode, count);
    }
}
