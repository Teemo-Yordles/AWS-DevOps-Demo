package com.unisys.aos.view.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.admin.entity.SmmAudit;
import com.unisys.aos.view.admin.mapper.SmmAuditMapper;
import com.unisys.aos.view.admin.service.SmmAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * audit information table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Service
public class SmmAuditServiceImpl extends ServiceImpl<SmmAuditMapper, SmmAudit> implements SmmAuditService {

    @Autowired
    SmmAuditMapper auditMapper;

    @Override
    public List<SmmAudit> findAudits() {
        //todo: paging
        return this.list();
    }

}
