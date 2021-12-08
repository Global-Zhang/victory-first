package com.hitTheRoad.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitTheRoad.server.mapper.MailLogMapper;
import com.hitTheRoad.server.pojo.*;
import com.hitTheRoad.server.mapper.EmployeeMapper;
import com.hitTheRoad.server.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitTheRoad.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailLogMapper mailLogMapper;

    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean maxWorkID() {
        //内置select功能
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        int i = Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1;
        String format = String.format("%08d", i);
        return RespBean.success(null,format);
    }

    @Override
    public RespBean addEmp(Employee emp) {
        LocalDate beginContract = emp.getBeginContract();
        LocalDate endContract = emp.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        emp.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        if (1==employeeMapper.insert(emp)){
            //发送邮件
            Employee employee = employeeMapper.getEmployee(emp.getId()).get(0);

            //数据库记录发送的信息
            //String msgId = UUID.randomUUID().toString();
            String msgId = "123456";
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(emp.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLogMapper.insert(mailLog);



            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                                            MailConstants.MAIL_ROUTING_KEY_NAME,
                                            emp,
                                            new CorrelationData(msgId));
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @Override
    public List<Employee> getEmployee(Integer id) {

        return employeeMapper.getEmployee(id);
    }

    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {

            //开启分页
            Page<Employee> page = new Page<>(currentPage,size);
            IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());

        return respPageBean;
    }


}
