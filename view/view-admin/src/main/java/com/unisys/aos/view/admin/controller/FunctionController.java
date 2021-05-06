package com.unisys.aos.view.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unisys.aos.view.admin.entity.SmmFunction;
import com.unisys.aos.view.admin.service.SmmFunctionService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * System function controller
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Slf4j
@RestController
@RequestMapping("/view/admin/functions")
public class FunctionController {
    @Autowired
    SmmFunctionService functionService;

    @GetMapping
    public Result<Collection<SmmFunction>> getFunctions() {
        log.info("Getting all function web service called...");
        List<SmmFunction> functionList = functionService.list();
        return Result.success(functionList);
    }

    /**
     * add new function
     * need to check if the function name is duplicate
     *
     * @param newFunction - new function to be added
     * @return - new function inserted into database
     */
    @PostMapping
    public Result<SmmFunction> add(@RequestBody SmmFunction newFunction) {
        log.info("adding function web service called...");
        // sanity check
        if (null == newFunction
                || StringUtils.isEmpty(newFunction.getCode())
                || StringUtils.isEmpty(newFunction.getDescription())) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Function code and description cannot be empty");
        }

        // check if function already exists
        QueryWrapper<SmmFunction> wrapper = new QueryWrapper<>();
        wrapper.eq("code", newFunction.getCode());
        int count = functionService.count(wrapper);
        if (count > 0) {
            return Result.failed(ResultCode.FUNCTION_EXISTED, "function code already existed");
        }

        SmmFunction insertedFunction;
        try {
            insertedFunction = functionService.addFunction(newFunction);
        } catch (DuplicateKeyException ex) {
            // this only happens when two users adding functions with same code at the same time.
            return Result.failed(ResultCode.FUNCTION_EXISTED, "function code already existed");
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during insert new function", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to insert new function. Please check admin log.");
        }
        return Result.success(insertedFunction);
    }

    /**
     * Delete function with ID
     *
     * @param id - function ID to be deleted
     * @return result code
     */
    @DeleteMapping("{id}")
    public Result<SmmFunction> deleteRole(@PathVariable("id") Long id) {
        log.info("deleting role web service called...");
        // sanity check
        if (null == id) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "function id is empty");
        }

        functionService.removeById(id);
        return Result.success(null);
    }

    /**
     * update a function
     * need to update function details
     *
     * @param function - function to be updated
     * @return updated function entity
     */
    @PutMapping
    public Result<SmmFunction> updateRole(@RequestBody SmmFunction function) {
        log.info("updating function web service called...");
        try {
            SmmFunction updatedFunction = functionService.updateFunction(function);
            if (null == updatedFunction) {
                return Result.failed(ResultCode.INVALID_VERSION, "function must have been updated by other user.");
            } else {
                return Result.success(updatedFunction);
            }
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during update function", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update function. Please check admin log.");
        }
    }
}

