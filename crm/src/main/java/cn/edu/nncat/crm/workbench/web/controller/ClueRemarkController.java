package cn.edu.nncat.crm.workbench.web.controller;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.domain.ReturnObject;
import cn.edu.nncat.crm.commons.utils.DateUtils;
import cn.edu.nncat.crm.commons.utils.UUIDUtils;
import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.workbench.domain.ClueRemark;
import cn.edu.nncat.crm.workbench.service.ClueRemarkService;
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
 * @create: 2022-11-07 19:05
 * @version:1.0
 **/
@Controller
public class ClueRemarkController {
    @Autowired
    ClueRemarkService clueRemarkService;

    @RequestMapping("workbench/clue/saveClueRemark.do")
    public @ResponseBody Object saveClueRemark(ClueRemark clueRemark, HttpSession session){
        ReturnObject returnObject=new ReturnObject();
        User user=(User)session.getAttribute(Contants.SESSION_USER);
        clueRemark.setCreateBy(user.getId());
        clueRemark.setId(UUIDUtils.getUUID());
        clueRemark.setCreateTime(DateUtils.formateDateTime(new Date()));
        clueRemark.setEditFlag(Contants.REMARK_EDIT_FLAG_NO_EDITED);
        try {
            int ret=clueRemarkService.insertClueRemark(clueRemark);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(clueRemark);
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

    @RequestMapping("/workbench/clue/updateClueRemarkById")
    public @ResponseBody Object updateClueRemarkById(ClueRemark clueRemark,HttpSession session){
        User user=(User)session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject=new ReturnObject();
        clueRemark.setEditBy(user.getId());
        clueRemark.setEditTime(DateUtils.formateDateTime(new Date()));
        clueRemark.setEditFlag(Contants.REMARK_EDIT_FLAG_YES_EDITED);
        try {
            int ret=clueRemarkService.updateClueRemarkById(clueRemark);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(clueRemark);
            }else{
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

    @RequestMapping("/workbench/clue/deleteClueRemarkById")
    public @ResponseBody Object deleteClueRemarkById(String id){
        ReturnObject returnObject=new ReturnObject();
        try {
            int ret=clueRemarkService.deleteClueRemarkById(id);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试。。");
            }
        }catch (Exception e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试。。");
            e.printStackTrace();
        }
        return returnObject;
    }
}
