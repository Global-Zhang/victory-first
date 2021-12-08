package com.hitTheRoad.server.service;

import com.hitTheRoad.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitTheRoad.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllDepartments();

    RespBean addDepartment(Department dep);

    RespBean deleteDepartment(Integer id);
}
