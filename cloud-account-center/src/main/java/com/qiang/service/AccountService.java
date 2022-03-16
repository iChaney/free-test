package com.qiang.service;

import com.qiang.pojo.RestResponse;

/**
 * @author liq
 * @date 2022/2/16 13:47
 */
public interface AccountService {
    RestResponse reduce(String userId, Integer money);
}
