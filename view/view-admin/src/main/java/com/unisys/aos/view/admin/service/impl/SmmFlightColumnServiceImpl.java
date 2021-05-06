package com.unisys.aos.view.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.admin.entity.SmmFlightColumn;
import com.unisys.aos.view.admin.mapper.SmmFlightColumnMapper;
import com.unisys.aos.view.admin.service.SmmFlightColumnService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 航班界面列数据表，配合smm_role_flight_column控制列访问权限 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-01-15
 */
@Service
public class SmmFlightColumnServiceImpl extends ServiceImpl<SmmFlightColumnMapper, SmmFlightColumn> implements SmmFlightColumnService {

}
