package com.hitTheRoad.server.service.impl;

import com.hitTheRoad.server.pojo.Admin;
import com.hitTheRoad.server.pojo.PoliticsStatus;
import com.hitTheRoad.server.mapper.PoliticsStatusMapper;
import com.hitTheRoad.server.service.IPoliticsStatusService;
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
public class PoliticsStatusServiceImpl extends ServiceImpl<PoliticsStatusMapper, PoliticsStatus> implements IPoliticsStatusService {



}
/*
* //一
    Admin admin = new Admin();
    Class clazz01 = admin.getClass();
    //二
    Class clazz02;

    {
        try {
            clazz02 = Class.forName("com.hitTheRoad.pojo.Admin");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //三
    Class clazz03 = Admin.class;
* */