package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-11 14:00
 * @version:1.0
 **/

public interface ActivityRemarkService {
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);
    int saveCreateActivityRemark(ActivityRemark remark);
    int deleteActivityRemarkById(String id);
    int updateActivityRemarkById(ActivityRemark remark);
}
