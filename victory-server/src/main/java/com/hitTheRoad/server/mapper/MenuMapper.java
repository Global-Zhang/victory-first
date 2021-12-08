package com.hitTheRoad.server.mapper;

import com.hitTheRoad.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> getMenuByAdminId(@Param("id") Integer id);

    List<Menu> getMenuWithRole();

    List<Menu> getAllMenus();
}
