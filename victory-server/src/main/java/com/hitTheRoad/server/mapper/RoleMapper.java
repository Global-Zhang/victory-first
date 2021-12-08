package com.hitTheRoad.server.mapper;

import com.hitTheRoad.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /*
    * 根据id获取role
    * */
    List<Role> getRoles(Integer adminId);
}
