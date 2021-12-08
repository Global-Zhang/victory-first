package com.hitTheRoad.server.service;

import com.hitTheRoad.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitTheRoad.server.pojo.Menu;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
public interface IAdminService extends IService<Admin> {

    Admin getAdminByUserName(String userName);

    RespBean login(String name, String password, String code,HttpServletRequest request);

    /*
    * 根据用户id查询角色列表
    * */
    List<Role> getRoles(Integer adminId);

    List<Admin> getAllAdmins(String keywords);

    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
