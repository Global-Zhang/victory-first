package com.hitTheRoad.server.mapper;

import com.hitTheRoad.server.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitTheRoad.server.pojo.RespBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Repository
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    int addAdminRole(@Param("adminId") Integer adminId,@Param("rids") Integer[] rids);
}
