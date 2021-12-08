package com.hitTheRoad.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hitTheRoad.server.pojo.Menu;
import com.hitTheRoad.server.pojo.MenuRole;
import com.hitTheRoad.server.mapper.MenuRoleMapper;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        //删除所有菜单
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (null == mids || 0 == mids.length){
            return RespBean.success("更新成功");
        }
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        return result == mids.length ? RespBean.success("更新成功") : RespBean.error("更新失败");
    }
}
