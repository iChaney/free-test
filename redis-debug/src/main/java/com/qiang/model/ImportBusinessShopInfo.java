package com.qiang.model;

import lombok.Data;

/**
 * @author liq
 * @date 2022/2/18 10:18
 */
@Data
public class ImportBusinessShopInfo {
    private String shopId;
    private String shopName;
    private String streetAddress;
    private String province;
    private String city;
    private String telPhone;
    private String shopImage;
    private Double txlat;
    private Double txlon;

    private String logo;
}
