package com.hitTheRoad.server.service.impl;

import com.hitTheRoad.server.AdminUtils;
import com.hitTheRoad.server.pojo.Admin;
import com.hitTheRoad.server.pojo.Menu;
import com.hitTheRoad.server.mapper.MenuMapper;
import com.hitTheRoad.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<Menu> getMenuByAdminId() {
        //获取id
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        //从redis获取菜单数据
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        //如果为空，去数据库获取
        if ( CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenuByAdminId(adminId);
            //将数据设置到Redis中
            valueOperations.set("menu_"+adminId,menus);
        }
        return menus;
    }

    //根据角色获取菜单
    @Override
    public List<Menu> getMenuWithRole(){
        return menuMapper.getMenuWithRole();
    }

    //查询所有菜单
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

}
