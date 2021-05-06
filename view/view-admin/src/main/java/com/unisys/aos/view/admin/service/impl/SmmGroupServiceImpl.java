package com.unisys.aos.view.admin.service.impl;

import com.unisys.aos.view.admin.entity.SmmGroup;
import com.unisys.aos.view.admin.mapper.SmmGroupMapper;
import com.unisys.aos.view.admin.service.SmmGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * group information table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Service
public class SmmGroupServiceImpl extends ServiceImpl<SmmGroupMapper, SmmGroup> implements SmmGroupService {

    @Autowired
    SmmGroupMapper groupMapper;

    @Override
    public List<SmmGroup> findGroups() {
        return this.list();
    }

    @Override
    public SmmGroup addGroup(SmmGroup newGroup) {
        groupMapper.insert(newGroup);
        return groupMapper.selectById(newGroup.getId());
    }

    @Override
    public SmmGroup updateGroup(SmmGroup group) {
        // update role details
        int updateCount = groupMapper.updateById(group);
        // optimistic locker check
        if (updateCount == 0) {
            return null;
        }
        return groupMapper.selectById(group.getId());
    }
}
