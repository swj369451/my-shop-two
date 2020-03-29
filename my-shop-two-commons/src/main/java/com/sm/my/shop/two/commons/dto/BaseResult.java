package com.sm.my.shop.two.commons.dto;

import lombok.Data;

@Data
public class BaseResult {

    public static final int SUCCESS_STATUS = 200;
    public static final int FAIL_STATUS = 500;

    private int status;
    private String message;
    private Object data;

    public static BaseResult success() {
        return createResult(SUCCESS_STATUS, "成功", null);
    }

    public static BaseResult success(String message) {
        return createResult(SUCCESS_STATUS, message, null);
    }

    public static BaseResult success(String message, Object data) {
        return createResult(SUCCESS_STATUS, message, data);
    }

    public static BaseResult fail() {
        return createResult(FAIL_STATUS, "失败", null);
    }

    public static BaseResult fail(String message) {
        return createResult(FAIL_STATUS, message, null);
    }

    public static BaseResult fail(int status, String message) {
        return createResult(status, message, null);
    }

    private static BaseResult createResult(int status, String message, Object data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setMessage(message);
        baseResult.setStatus(status);
        baseResult.setData(data);
        return baseResult;
    }
}
