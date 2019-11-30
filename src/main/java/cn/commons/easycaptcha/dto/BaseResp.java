package cn.commons.easycaptcha.dto;

import lombok.Data;

@Data
public class BaseResp<T> {

    private int code;

    private String msg;

    private T data;

    public static <T> BaseResp<T> success() {
        return success(null);
    }

    public static <T> BaseResp<T> success(T obj) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setData(obj);
        resp.setCode(200);
        return resp;
    }

    public static BaseResp fail(String msg) {
        BaseResp resp = new BaseResp();
        resp.setCode(500);
        resp.setMsg(msg);
        return resp;
    }
}