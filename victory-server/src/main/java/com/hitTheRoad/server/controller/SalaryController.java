package com.hitTheRoad.server.controller;


import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.pojo.Salary;
import com.hitTheRoad.server.service.ISalaryService;
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
@RequestMapping("/salary/sub")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "获取所有工资张套")
    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资张套")
    @PostMapping("/")
    public RespBean saveSalary(@RequestBody Salary salary){
        return salaryService.save(salary) ? RespBean.success("添加成功") : RespBean.error("添加失败");
    }

    @ApiOperation(value = "删除工资张套")
    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable("id") Integer id){
        return salaryService.removeById(id) ? RespBean.success("删除成功") : RespBean.error("删除失败");
    }

    @ApiOperation(value = "更新工资张套")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary){
        return salaryService.updateById(salary) ? RespBean.success("更新成功") : RespBean.error("更新失败");
    }

}
