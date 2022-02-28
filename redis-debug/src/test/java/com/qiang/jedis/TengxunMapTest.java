package com.qiang.jedis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qiang.mapper.ShopbaseDOMapper;
import com.qiang.model.ImportBusinessResponse;
import com.qiang.model.ImportBusinessShopInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liq
 * @date 2022/2/18 10:13
 */
@Slf4j
@SpringBootTest
public class TengxunMapTest {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ShopbaseDOMapper shopbaseDOMapper;

    private static String IMPORT_URL = "https://api.weixin.qq.com/cityservice/importbusinesspoidata?access_token" +
                                       "=54_acFSL-cs2JfVtnmooQwPoxPZ0TCybOcwylC8Fk-yNkiQyTria5BUbAJURYFB7QcUeKlIVk6PTOU_dDk2TYUa-t8heSgxEaMWXGtgngJ1i_uMI_EsEAqTrK0kaoXGWPxt2KXpdUMjGfRKlanzBAWhAHAUTP";


    @Test
    @DisplayName("向腾讯地图导入门店信息")
    public void importBusinessData() {
        /**
         * 获取accesstoken
         *
         * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx4a68a5b1b2d89fea&secret=926be762ce82d37b7c6b19e802af010f
         */
        List<Map<String, Object>> errorShops = new ArrayList<>();
        List<ImportBusinessShopInfo> shopInfoList = getImportBusinessShopInfoList();
        for (ImportBusinessShopInfo shopInfo : shopInfoList) {
            JSONObject params = new JSONObject();
            try {
                installParams(shopInfo, params);    // 封装参数
            } catch (Exception e) {
                Map<String, Object> map = new HashMap<>();
                map.put(shopInfo.getShopId(), e.getMessage());
                errorShops.add(map);
                continue;
            }
            // 发送请求
            System.out.println(params.toJSONString());
            ImportBusinessResponse response = restTemplate.postForObject(IMPORT_URL, params, ImportBusinessResponse.class);
            if (response.getErrcode() != 0) {
                Map<String, Object> map = new HashMap<>();
                map.put(shopInfo.getShopId(), response.getErrcode() + " // " + response.getErrmsg());
                errorShops.add(map);
            }
        }
        // 保存和打印错误门店
        if (!CollectionUtils.isEmpty(errorShops)) {
            JSONArray errJsonArray = new JSONArray();
            errJsonArray.addAll(errorShops);
            stringRedisTemplate.opsForValue().set("sjz::import_error_shop", errJsonArray.toJSONString(), 90, TimeUnit.DAYS);
            log.info("错误的门店有:{}", errJsonArray.toJSONString());
        }

    }

    private void installParams(ImportBusinessShopInfo shopInfo, JSONObject params) {
        params.put("ud_id", "1");
        params.put("status", 1);
        params.put("title", shopInfo.getShopName());
        params.put("category", "酒店宾馆");
        params.put("address", shopInfo.getStreetAddress());
        params.put("province", shopInfo.getProvince());
        params.put("city", shopInfo.getCity());
        params.put("latitude", shopInfo.getTxlat());        // 需要确认
        params.put("longitude", shopInfo.getTxlon());       // 需要确认
        JSONArray shopImageArray = new JSONArray();
        List<String> hotelPics = shopbaseDOMapper.selectHotelImage(shopInfo.getShopId());
        if (CollectionUtils.isEmpty(hotelPics)) {
            if (!StringUtils.isEmpty(shopInfo.getLogo())) {
                hotelPics = Lists.newArrayList(shopInfo.getLogo());
            }
        }
        if (CollectionUtils.isEmpty(hotelPics)) {
            throw new RuntimeException("没有找到门店图片");
        }
        for (String hotelPic : hotelPics) {
            JSONObject picJsonObject = new JSONObject();
            picJsonObject.put("url", hotelPic);
            shopImageArray.add(picJsonObject);
        }

        params.put("shop_photo_list", shopImageArray);
        params.put("tel", shopInfo.getTelPhone());
        params.put("contact_tel", shopInfo.getTelPhone());

        // 需要确认
        params.put("app_id", "wx4a68a5b1b2d89fea");
        params.put("app_name", "心里美酒店预定尚客优骏怡兰欧");
        params.put("app_icon", "https://picture.ethank.com.cn/20220221/180.png");
        params.put("app_origin_id", "gh_c94458e36387");
        JSONArray pages = new JSONArray();
        JSONObject page1 = new JSONObject();
        page1.put("name", "首页");
        page1.put("path", "pages/index/index");
        page1.put("rank", 1);
        pages.add(page1);
        params.put("pages", pages);

        // 营业时间
        JSONObject openTime = new JSONObject();
        openTime.put("weekdays", new int[]{1,2,3,4,5,6,7});
        openTime.put("open", "00:00");
        openTime.put("close", "23:59");
        JSONArray openingTime = new JSONArray();
        openingTime.add(openTime);
        params.put("opening_time", openingTime);
    }

    // 从数据库查需要导入的门店信息
    public List<ImportBusinessShopInfo> getImportBusinessShopInfoList() {
        return shopbaseDOMapper.importBusinessShopInfoList("00192");
    }
}
