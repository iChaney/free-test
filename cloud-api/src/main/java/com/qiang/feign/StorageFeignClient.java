package com.qiang.feign;

import com.qiang.pojo.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liq
 * @date 2022/2/16 11:14
 */
@FeignClient(name = "storage-center")
public interface StorageFeignClient {

    @PostMapping("/reduce")
    RestResponse reduce(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
