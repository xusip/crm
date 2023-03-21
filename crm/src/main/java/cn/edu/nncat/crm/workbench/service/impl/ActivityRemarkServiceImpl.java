package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.workbench.domain.ActivityRemark;
import cn.edu.nncat.crm.workbench.mapper.ActivityRemarkMapper;
import cn.edu.nncat.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-11 14:02
 * @version:1.0
 **/
@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }

    @Override
    public int saveCreateActivityRemark(ActivityRemark remark) {
        return activityRemarkMapper.insertActivityRemark(remark);
    }

    @Override
    public int deleteActivityRemarkById(String id) {
        return activityRemarkMapper.deleteActivityRemarkById(id);
    }

    @Override
    public int updateActivityRemarkById(ActivityRemark remark) {
        return activityRemarkMapper.updateActivityRemarkById(remark);
    }
}
