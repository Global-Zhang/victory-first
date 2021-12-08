package com.hitTheRoad.server.controller;


import com.hitTheRoad.server.pojo.Admin;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.pojo.Role;
import com.hitTheRoad.server.service.IAdminService;
import com.hitTheRoad.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords){

        return  adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean updateAdmin(@PathVariable("id") String id){
        if (adminService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "更新所有角色")
    @GetMapping("/role")
    public RespBean updateAdminRole(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }
}
