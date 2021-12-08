package com.hitTheRoad.server.controller;

import com.hitTheRoad.server.pojo.Admin;
import com.hitTheRoad.server.pojo.AdminLoginParam;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getName(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("退出成功");
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/adminInfo")
    public Admin getAdminInfo(Principal principal){
        if (null == principal) return null;

        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return  admin;
    }
}
