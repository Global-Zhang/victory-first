package com.hitTheRoad.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*
* 用户登录实体类
* */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "adminLogin对象",description ="" )
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
