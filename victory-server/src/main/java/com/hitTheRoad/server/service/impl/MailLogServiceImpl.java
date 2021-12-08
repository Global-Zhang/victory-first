package com.hitTheRoad.server.service.impl;

import com.hitTheRoad.server.pojo.MailLog;
import com.hitTheRoad.server.mapper.MailLogMapper;
import com.hitTheRoad.server.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
