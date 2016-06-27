package org.seckill.dto;

/**
 * Created by LJ on 2016/6/24.
 */
//所有AJAX请求返回类型，封装json结果
public class SeckillResult<T> {

    private  boolean success;

    private T date;

    private String error;

    public SeckillResult(boolean success,String error) {
        this.error = error;
        this.success = success;
    }

    public SeckillResult(boolean success,T date) {
        this.date = date;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
