package com.hitTheRoad.server.service;

import com.hitTheRoad.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitTheRoad.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
public interface IMenuRoleService extends IService<MenuRole> {

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
