package com.unisys.aos.view.admin.controller;


import com.unisys.aos.view.admin.entity.SmmAudit;
import com.unisys.aos.view.admin.service.SmmAuditService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * audit information table 前端控制器
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Slf4j
@RestController
@RequestMapping("/view/admin/audits")
public class AuditController {
    @Autowired
    SmmAuditService auditService;

    // query audit list
    @GetMapping
    public Result<Collection<SmmAudit>> getAudits() {
        log.info("Get all audits...");
        List<SmmAudit> auditList;
        try {
            auditList = auditService.findAudits();
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during getting audits", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to get audit list. Please check admin log.");
        }
        return Result.success(auditList);
    }

}

