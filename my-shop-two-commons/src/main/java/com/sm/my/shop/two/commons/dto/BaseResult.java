package com.sm.my.shop.two.commons.dto;

public class BaseResult {

    public static final int SUCCESS_STATUS=200;
    public static final int FAIL_STATUS=500;

    private int status;
    private String message;

    public static BaseResult success(){
        return createResult(SUCCESS_STATUS,"成功");

    }

    public static BaseResult success(String message){
        return createResult(SUCCESS_STATUS,message);

    }

    public static BaseResult fail(){
        return createResult(FAIL_STATUS,"失败");
    }

    public static BaseResult fail(String message){
        return createResult(FAIL_STATUS,message);
    }
    public static BaseResult fail(int status,String message){
        return createResult(status,message);
    }
    private static BaseResult createResult(int status,String message){
        BaseResult baseResult = new BaseResult();
        baseResult.setMessage(message);
        baseResult.setStatus(status);
        return baseResult;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
