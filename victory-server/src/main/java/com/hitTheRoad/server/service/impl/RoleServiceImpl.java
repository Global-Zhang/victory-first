package com.hitTheRoad.server.service.impl;

import com.hitTheRoad.server.pojo.Role;
import com.hitTheRoad.server.mapper.RoleMapper;
import com.hitTheRoad.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
