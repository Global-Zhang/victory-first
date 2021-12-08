package com.hitTheRoad.server.service;

import com.hitTheRoad.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitTheRoad.server.pojo.Nation;
import com.hitTheRoad.server.pojo.RespBean;
import com.hitTheRoad.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean maxWorkID();

    RespBean addEmp(Employee emp);

    List<Employee> getEmployee(Integer id);


    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
