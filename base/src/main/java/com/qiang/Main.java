package com.qiang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author: liq
 * @date: 2021/6/10 21:12
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws AlipayApiException {
        Map<Object, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("xiaoming", 19);
        hashMap.put("feer", 20);
        hashMap.put("jiko", 21);
        Set<Map.Entry<Object, Object>> entries = hashMap.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey()+"::"+entry.getValue());
        }
    }



    private static void testJson() throws JsonProcessingException {
        JSONObject student = new JSONObject();
        JSONObject xiaoming = new JSONObject();
        xiaoming.put("name", "小明");
        xiaoming.put("age", 18);
        student.put("xiaoming", xiaoming);
        System.out.println(student.toJSONString());


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode student2 = objectMapper.createObjectNode();
        student2.putObject("xiaoming").put("name", "小明").put("age", 18);
        System.out.println(objectMapper.writeValueAsString(student2));
    }


    private static void printException() {
        try{
            int i = 1/0;
        }catch (Exception e) {
            log.error("发生错误, hotel={}", "23444", e);
        }
    }

    private static void verfy() throws AlipayApiException {
        Map map = JSON.parseObject("{\n" + "    \"app_id\":\"2019071165793332\",\n" + "    \"auth_app_id\":\"2019071165793332\",\n" +
                                   "    \"buyer_id\":\"2088622814889493\",\n" + "    \"buyer_logon_id\":\"182****5059\",\n" +
                                   "    \"buyer_pay_amount\":\"144.17\",\n" + "    \"charset\":\"utf-8\",\n" +
                                   "    \"fund_bill_list\":\"[{\\\"amount\\\":\\\"144.17\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"},{\\\"amount\\\":\\\"1.83\\\",\\\"fundChannel\\\":\\\"DISCOUNT\\\"}]\",\n" +
                                   "    \"gmt_create\":\"2021-12-26 21:19:08\",\n" + "    \"gmt_payment\":\"2021-12-26 21:19:19\",\n" +
                                   "    \"invoice_amount\":\"144.17\",\n" + "    \"notify_id\":\"2021122600222211919089491440040580\",\n" +
                                   "    \"notify_time\":\"2021-12-27 06:47:32\",\n" + "    \"notify_type\":\"trade_status_sync\",\n" +
                                   "    \"out_trade_no\":\"YD04506211226211904137\",\n" + "    \"point_amount\":\"0.00\",\n" +
                                   "    \"receipt_amount\":\"146.00\",\n" + "    \"seller_email\":\"zhifubaomp@1615.cn\",\n" +
                                   "    \"seller_id\":\"2088531637458631\",\n" +
                                   "    \"sign\":\"J+3GKL8WXViyg5fH066NLUrSZEuKT95OuoeQqbz8BFGf4uvxEUfkKm0/gTXQKLKLG6QiBxvyKIzWAO3J2eE6lYZPjlN8WsBAVAODayK82NKSY03QRc0uaIvqhga1WssZOOte8z6GPMaQq3KbUUxIzO+wkRRk39Pme3JCkYZbRKBdHWhtiswmsIkpJQ9rZ9H5S0Yk9xZTio9VXQnJ6ChvPNgYvYYbkgCoqCpxgfv7qkBSvRZfNMe95A5vCbQarNsqOTkk+mH2xs/ziuZ94kzggME0Fx2LmdZnC7y7/fGd4ZSnG0K4mFiYW2nhxr5x8awEErW83+beRJADZ9I4edNcxQ==\",\n" +
                                   "    \"sign_type\":\"RSA2\",\n" + "    \"subject\":\"尚客优精选山西朔州朔城区张辽南路店标准大床房\",\n" +
                                   "    \"total_amount\":\"146.00\",\n" + "    \"trade_no\":\"2021122622001489491450614271\",\n" +
                                   "    \"trade_status\":\"TRADE_SUCCESS\",\n" + "    \"version\":\"1.0\"\n" + "}", Map.class);
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh+yjnAkwMUdPoqZAlP3IpFCah4paPxZ2WcRJ7IwEftlRWIoTpjZ2/qREMk9+taSqVZM8FCzqojDRDjrhk5wuCOvKmkamCAm82bhoUFE2y0UVZxkbI46wlQ2xYLnuMabupfXCDxJIN4SVNs0Ie8iSDojz2L9pWpIz/wtlfW5W/WnwF30gWGAD/IYNsUl1GJ6R7BvP8ACLsM1W/3ipSTWCRpstOhMe1daUyBSq5CXAbyP6VxOxIIyvDYmg6OTNL5i/a7k9DfjzgGb/rRXPQrV+qh/vwjIUA84F1xrVNNli1kP+/qhOfYzhBlfvCjUtxQoVO3BF5OD23Kl+DlHBA+WtTwIDAQAB";
        boolean ver = AlipaySignature.verifyV1(map, publicKey, "utf-8", "RSA2");
        System.out.println(ver);
    }

}
