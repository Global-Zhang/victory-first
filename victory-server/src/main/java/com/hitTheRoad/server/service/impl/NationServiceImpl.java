package com.hitTheRoad.server.service.impl;

import com.hitTheRoad.server.pojo.Nation;
import com.hitTheRoad.server.mapper.NationMapper;
import com.hitTheRoad.server.service.INationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
