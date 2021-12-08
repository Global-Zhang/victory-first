package com.hitTheRoad.server.pojo;

import java.util.List;

//分页公共返回对象
public class RespPageBean {

    /**
     * 总条数
     */
    private Long total;

    public RespPageBean() {
    }

    public RespPageBean(Long total, List<?> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    /**
     * 总条数
     */
    private List<?> data;
}
