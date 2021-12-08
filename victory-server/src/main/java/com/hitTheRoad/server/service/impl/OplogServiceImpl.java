package com.hitTheRoad.server.service.impl;

import com.hitTheRoad.server.pojo.Oplog;
import com.hitTheRoad.server.mapper.OplogMapper;
import com.hitTheRoad.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
