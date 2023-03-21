package cn.edu.nncat.crm.workbench.web.controller;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.domain.ReturnObject;
import cn.edu.nncat.crm.commons.utils.UUIDUtils;
import cn.edu.nncat.crm.workbench.domain.Activity;
import cn.edu.nncat.crm.workbench.domain.ClueActivityRelation;
import cn.edu.nncat.crm.workbench.service.ActivityRemarkService;
import cn.edu.nncat.crm.workbench.service.ActivityService;
import cn.edu.nncat.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-11-13 15:19
 * @version:1.0
 **/
@Controller
public class ClueActivityRelationController {
    @Autowired
    ActivityService activityService;

    @Autowired
    ClueActivityRelationService clueActivityRelationService;

    @RequestMapping("/workbench/clue/selectActivityListByName.do")
    public @ResponseBody Object selectActivityListByName(String name,String clueId){
        List<Activity> activityList=activityService.selectActivityListByName(name,clueId);
        ReturnObject returnObject=new ReturnObject();
        if(activityList!=null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(activityList);
        }else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("未查询到相关的市场活动！");
        }
        return returnObject;
    }

    /*
    自己写的
    @RequestMapping("/workbench/clue/saveClueActivityRelationByActId.do")
    public @ResponseBody Object saveClueActivityRelationByActId(String[] id,String clueId){
        ClueActivityRelation clueActivityRelation =new ClueActivityRelation();
        List<Activity> activityList=new ArrayList<>();
        ReturnObject returnObject=new ReturnObject();
        Activity activity=new Activity();
        int ret=0;
        String uuid="";
        try {
            for (int i = 0; i < id.length; i++) {
                uuid=UUIDUtils.getUUID();
                clueActivityRelation.setId(uuid);
                clueActivityRelation.setActivityId(id[i]);
                clueActivityRelation.setClueId(clueId);
                int num = clueActivityRelationService.saveClueActivityRelationByActId(clueActivityRelation);
                activity=activityService.queryActivityForDetailById(id[i]);
                activity.setId(uuid);
                activityList.add(activity);
                ret += ret;
                System.out.println("i=" + i + "num=" + num);
            }
            System.out.println(ret);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(activityList);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试。。。");
        }
        return returnObject;
    }*/

    @RequestMapping("/workbench/clue/saveClueActivityRelationByList.do")
    public @ResponseBody Object saveClueActivityRelationByList(String[] activityId,String clueId){
        List<ClueActivityRelation> clueActivityRelationList =new ArrayList<>();
        ClueActivityRelation clueActivityRelation=null;

        ReturnObject returnObject=new ReturnObject();

            for (String ai:activityId) {
                clueActivityRelation=new ClueActivityRelation();
                clueActivityRelation.setId(UUIDUtils.getUUID());
                clueActivityRelation.setActivityId(ai);
                clueActivityRelation.setClueId(clueId);
                System.out.println(clueActivityRelation.toString());
                clueActivityRelationList.add(clueActivityRelation);
            }

        try {
            int ret = clueActivityRelationService.saveClueActivityRelationByList(clueActivityRelationList);
            if(ret>0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                List<Activity> activityList=activityService.queryActivityForDetailByIds(activityId);
                returnObject.setRetData(activityList);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/clue/deleteClueActivityRelationByClueIdActId.do")
    public @ResponseBody Object deleteClueActivityRelationByActId(ClueActivityRelation clueActivityRelation){
        ReturnObject returnObject=new ReturnObject();
        try{
            int ret=clueActivityRelationService.deleteClueActivityRelationByClueIdActId(clueActivityRelation);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setMessage(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后再试！！");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setMessage(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后再试！！");
        }
        return returnObject;
    }
}
