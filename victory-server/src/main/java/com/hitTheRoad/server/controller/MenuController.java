package com.hitTheRoad.server.controller;


import com.hitTheRoad.server.pojo.Menu;
import com.hitTheRoad.server.service.IAdminService;
import com.hitTheRoad.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenubyAdminId(){
        return menuService.getMenuByAdminId();
    }

}
