package com.qiang.service.impl;

import com.qiang.mapper.AccountPOMapper;
import com.qiang.pojo.RestResponse;
import com.qiang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liq
 * @date 2022/2/16 13:48
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountPOMapper accountPOMapper;

    @Override
    public RestResponse reduce(String userId, Integer money) {
        accountPOMapper.updateMoney(userId, money);
        return RestResponse.success();
    }
}
