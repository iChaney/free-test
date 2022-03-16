package com.qiang.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liq
 * @date 2022/2/11 14:11
 */
@NoArgsConstructor
@Setter
@Getter
public class RestResponse<T> implements Serializable {
    private static final long serialVersionUID = -6664353401112601303L;

    private Integer code;
    private String message;
    private T data;

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse(200, "成功", data);
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse(200, "成功", null);
    }

    public static <T> RestResponse<T> fail(String message) {
        return new RestResponse(444, "失败", null);
    }

    public RestResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
