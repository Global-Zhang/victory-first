package com.hitTheRoad.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hitTheRoad.server.pojo.Employee;
import com.hitTheRoad.server.pojo.MailConstants;
import com.hitTheRoad.server.pojo.MailLog;
import com.hitTheRoad.server.service.IEmployeeService;
import com.hitTheRoad.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
    * 邮件发送定时任务
    * 18秒执行一次
    * */
    @Scheduled(cron = "8/10 * * * * ?")
    public void mailTasks(){
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>()
                                                    .eq("status", 0)
                                                    .lt("tryTime", LocalDateTime.now()));
        list.forEach(mailLog -> {

            if (3<=mailLog.getCount()){
                mailLogService.update(new UpdateWrapper<MailLog>()
                                                .set("status",2)
                                                .eq("msgId",mailLog.getMsgId()));
            }


            mailLogService.update(new UpdateWrapper<MailLog>()
                                                .set("count",mailLog.getCount()+1)
                                                .set("updateTime",LocalDateTime.now())
                                                .set("tryTime",LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT))
                                                .eq("msgId",mailLog.getMsgId()));
            //发送消息
            Employee employee = employeeService.getEmployee(mailLog.getEid()).get(0);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                                                MailConstants.MAIL_ROUTING_KEY_NAME,
                                                employee,
                                                new CorrelationData(mailLog.getMsgId()));

        });

    }
}
