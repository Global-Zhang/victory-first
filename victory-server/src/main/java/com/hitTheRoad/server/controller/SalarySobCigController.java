package com.hitTheRoad.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hitTheRoad.server.pojo.Employee;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.pojo.RespPageBean;
import com.hitTheRoad.server.pojo.Salary;
import com.hitTheRoad.server.service.IEmployeeEcService;
import com.hitTheRoad.server.service.IEmployeeService;
import com.hitTheRoad.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCigController {

    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;

    //获取所有账套
    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> selectAll() {
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工账套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid,Integer sid){
        boolean update = employeeService.update(new UpdateWrapper<Employee>().set("salaryId", sid).eq("id", eid));
        return update ? RespBean.success("更新成功") : RespBean.error("更新失败");
    }
}
