package com.qiang.feign;

import com.qiang.pojo.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liq
 * @date 2022/2/16 10:40
 */
@FeignClient(value = "account-center")
public interface AccountFeignClient {
    @PostMapping("/reduce")
    RestResponse reduce(@RequestParam("userId") String userId, @RequestParam("money") Integer money);
}
