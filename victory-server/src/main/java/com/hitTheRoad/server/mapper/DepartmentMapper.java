package com.hitTheRoad.server.mapper;

import com.hitTheRoad.server.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.MapperConfig;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer parentId);

    void addDepartment(Department dep);

    void deleteDepartment(Department dep);
}
