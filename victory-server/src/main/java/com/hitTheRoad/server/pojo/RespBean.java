package com.hitTheRoad.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/*
 * 公共返回对象
 * */

public class RespBean {
    private long code;
    private String message;
    private Object obj;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public RespBean(long code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    /*
     * 成功返回结果
     * */
    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }

    public static RespBean success(String message, Object obj) {
        return new RespBean(200, message, obj);
    }

    /*
     * 失败返回结果
     * */
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    public static RespBean error(String message, Object obj) {
        return new RespBean(500, message, obj);
    }

    public void setCode() {
    }
}
