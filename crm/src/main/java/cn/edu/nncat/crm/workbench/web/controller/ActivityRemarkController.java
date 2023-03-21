package cn.edu.nncat.crm.workbench.web.controller;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.domain.ReturnObject;
import cn.edu.nncat.crm.commons.utils.DateUtils;
import cn.edu.nncat.crm.commons.utils.UUIDUtils;
import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.workbench.domain.ActivityRemark;
import cn.edu.nncat.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-12 16:53
 * @version:1.0
 **/
@Controller
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/saveCreateActivityRemark.do")
    public @ResponseBody Object saveCreateActivityRemark(ActivityRemark remark, HttpSession session){
        User user=(User)session.getAttribute(Contants.SESSION_USER);
        remark.setId(UUIDUtils.getUUID());
        remark.setCreateTime(DateUtils.formateDateTime(new Date()));
        remark.setCreateBy(user.getId());
        remark.setEditFlag(Contants.REMARK_EDIT_FLAG_NO_EDITED);
        ReturnObject returnObject=new ReturnObject();
        try {
            int ret=activityRemarkService.saveCreateActivityRemark(remark);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(remark);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/deleteActivityRemarkById.do")
    public @ResponseBody Object deleteActivityRemarkById(String id){
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret=activityRemarkService.deleteActivityRemarkById(id);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试。。。");
            }
        }catch (Exception e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/updateActivityRemarkById.do")
    public @ResponseBody Object updateActivityRemarkById(ActivityRemark remark,HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject=new ReturnObject();
        remark.setEditTime(DateUtils.formateDateTime(new Date()));
        remark.setEditFlag(Contants.REMARK_EDIT_FLAG_YES_EDITED);
        remark.setEditBy(user.getId());
        try{
            int ret=activityRemarkService.updateActivityRemarkById(remark);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(remark);
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
}
