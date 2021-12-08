package com.hitTheRoad.server.mapper;

import com.hitTheRoad.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.MapperConfig;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Mapper
@Repository
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    //批量更新
    Integer insertRecord(@Param("rid") Integer rid,@Param("mids") Integer[] mids);
}
