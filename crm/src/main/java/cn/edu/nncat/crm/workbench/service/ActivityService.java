package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.Activity;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-15 17:22
 * @version:1.0
 **/

public interface ActivityService {
    int saveCreateActivity(Activity activity);
    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);
    int queryCountOfActivityByCondition(Map<String,Object> map);
    int deleteActivityByIds(String[] ids);
    int updateActivityById(Activity activity);
    Activity selectActivityById(String id);
    List<Activity> queryAllActivitys();
    int saveCreateActivityByList(List<Activity> activityList);
    Activity queryActivityForDetailById(String id);
    List<Activity> selectActivityListByName(String name,String clueId);
    List<Activity> queryActivityForDetailByIds(String[] ids);
    List<Activity> selectActivityForConvertByNameClueId(Map<String,Object> map);
    List<Activity> selectActivityListForAll(String name);
}
