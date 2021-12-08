package com.hitTheRoad.server.controller;


import com.hitTheRoad.server.pojo.Department;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    //涉及到数据库存储路径
    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department dep){
        return departmentService.addDepartment(dep);
    }

    //涉及到数据库存储路径
    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable("id") Integer id){
        return departmentService.deleteDepartment(id);
    }

}
