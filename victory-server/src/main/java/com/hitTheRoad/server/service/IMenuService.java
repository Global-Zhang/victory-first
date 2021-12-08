package com.hitTheRoad.server.service;

import com.hitTheRoad.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenuByAdminId();

    List<Menu> getMenuWithRole();

    List<Menu> getAllMenus();
}
