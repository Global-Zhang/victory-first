package com.hitTheRoad.server.mapper;

import com.hitTheRoad.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitTheRoad.server.pojo.Menu;
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
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
              //getAllAdmins
    List<Admin> getAllAdmins(@Param("id")Integer id,@Param("keywords") String keywords);
}
