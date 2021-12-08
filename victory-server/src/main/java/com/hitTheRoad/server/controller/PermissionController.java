package com.hitTheRoad.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hitTheRoad.server.pojo.Menu;
import com.hitTheRoad.server.pojo.MenuRole;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.pojo.Role;
import com.hitTheRoad.server.service.IMenuRoleService;
import com.hitTheRoad.server.service.IMenuService;
import com.hitTheRoad.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* 权限组
* springSecurity 要求角色前加  ROLE_
* */
@RestController
public class PermissionController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.list();
    }


    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public RespBean addAllRoles(@RequestBody Role role){
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)){
            return RespBean.success("添加角色成功");
        }
        return RespBean.error("添加角色失败");
    }

    /*@ApiOperation(value = "更新角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role){
        if (roleService.updateById(role)){
            return RespBean.success("更新角色成功");
        }
        return RespBean.error("更新角色失败");
    }*/

    @ApiOperation(value ="删除单个角色")
    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable("id") Integer rid){
        if (roleService.removeById(rid)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /*@ApiOperation(value ="批量删除角色")
    @DeleteMapping("/")
    public RespBean deleteRolesByIds(Integer[] ids){
        if (roleService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }*/

    /*
    * 有子菜单，不能捡现成
    * */
    @ApiOperation(value = "获取所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable("rid") Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>()
                .eq("rid",rid))
                .stream()
                .map(MenuRole::getMid)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }

}
