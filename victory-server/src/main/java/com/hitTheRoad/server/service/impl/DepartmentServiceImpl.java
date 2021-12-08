package com.hitTheRoad.server.service.impl;

import com.hitTheRoad.server.pojo.Department;
import com.hitTheRoad.server.mapper.DepartmentMapper;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    public DepartmentMapper departmentMapper;

    //利用xml实现递归，传参-1是第一级的父id。和菜单功能试着互换
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    @Override
    public RespBean addDepartment(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDepartment(dep);
        if (1 == dep.getResult()){
            return RespBean.success("添加成功",dep);
        }
        return RespBean.error("添加失败");
    }

    //查看存储路径
    @Override
    public RespBean deleteDepartment(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDepartment(department);
        if (-2 == department.getResult()){
            return RespBean.error("该部门还有子部门，删除失败");
        }
        if (-1 == department.getResult()){
            return RespBean.error("该部门还有员工，删除失败");
        }
        if (1 == department.getResult()){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
