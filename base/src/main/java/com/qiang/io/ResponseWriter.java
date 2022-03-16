package com.qiang.io;

/**
 * @author: liq
 * @date: 2021/7/3 15:39
 */
public class ResponseWriter {

    public static String buildResponse() {
        String HTTP_SEPARATOR = "\r\n";
        String body = "<h1>hello server</h1>";
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK").append(HTTP_SEPARATOR);
        sb.append("Content-Type: text/html; charset=UTF-8").append(HTTP_SEPARATOR);
        sb.append("Content-Length:").append(body.length()).append(HTTP_SEPARATOR);
        sb.append(HTTP_SEPARATOR);
        sb.append(body);
        return sb.toString();
    }
}
