package com.unisys.aos.view.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unisys.aos.view.admin.entity.SmmAudit;

import java.util.List;

/**
 * <p>
 * audit information table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
public interface SmmAuditService extends IService<SmmAudit> {
    /**
     * find all audits in DB.
     * @return
     */
    List<SmmAudit> findAudits();

}
