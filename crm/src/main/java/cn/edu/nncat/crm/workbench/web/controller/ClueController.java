package cn.edu.nncat.crm.workbench.web.controller;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.domain.ReturnObject;
import cn.edu.nncat.crm.commons.utils.DateUtils;
import cn.edu.nncat.crm.commons.utils.UUIDUtils;
import cn.edu.nncat.crm.settings.domain.DicValue;
import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.settings.service.DicValueService;
import cn.edu.nncat.crm.settings.service.UserService;
import cn.edu.nncat.crm.workbench.domain.Activity;
import cn.edu.nncat.crm.workbench.domain.Clue;
import cn.edu.nncat.crm.workbench.domain.ClueRemark;
import cn.edu.nncat.crm.workbench.service.ActivityService;
import cn.edu.nncat.crm.workbench.service.ClueRemarkService;
import cn.edu.nncat.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-17 21:45
 * @version:1.0
 **/
@Controller
public class ClueController {
    @Autowired
    private UserService userService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest request){
        List<User> userList=userService.queryAllUsers();
        List<DicValue> appellationList=dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueStateList=dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        request.setAttribute("userList",userList);
        request.setAttribute("appellationList",appellationList);
        request.setAttribute("clueStateList",clueStateList);
        request.setAttribute("sourceList",sourceList);
        return "workbench/clue/index";
    }

    @RequestMapping("/workbench/clue/saveCreateClue.do")
    public @ResponseBody Object saveCreateClue(Clue clue, HttpSession session){
        User user=(User)session.getAttribute(Contants.SESSION_USER);
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.formateDateTime(new Date()));
        clue.setCreateBy(user.getId());
        ReturnObject returnObject=new ReturnObject();
        try{
            int ret=clueService.saveCreateClue(clue);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后再试。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后再试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/clue/queryClueByConditionForPage.do")
    public @ResponseBody Object queryClueByConditionForPage(String fullname,String company,String phone,String source,String owner,
                                                            String mphone,String clueState,int pageNo,int pageSize){
        Map<String,Object> map=new HashMap<>();
        map.put("fullname",fullname);
        map.put("company",company);
        map.put("phone",phone);
        map.put("source",source);
        map.put("owner",owner);
        map.put("mphone",mphone);
        map.put("state",clueState);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Clue> clueList=clueService.queryClueByConditionForPage(map);
        int totalRows=clueService.queryCountOfClueByCondition(map);
        Map<String,Object> retMap=new HashMap<>();
        retMap.put("clueList",clueList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("workbench/clue/updateClueById.do")
    public @ResponseBody Object updateClueById(Clue clue,HttpSession session){
        User user=(User)session.getAttribute(Contants.SESSION_USER);
        clue.setEditBy(user.getId());
        clue.setEditTime(DateUtils.formateDate(new Date()));
        ReturnObject returnObject=new ReturnObject();
        int ret=clueService.updateClueById(clue);
        try {
            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试。。。");
            }
        }catch (Exception e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试。。。");
            e.printStackTrace();
        }
        return returnObject;
    }

    @RequestMapping("workbench/clue/selectClueByPrimaryKey.do")
    public @ResponseBody Object selectClueByPrimaryKey(String id){
        Clue clue=clueService.selectClueByPrimaryKey(id);
        ReturnObject returnObject=new ReturnObject();
        if(clue!=null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(clue);
        }else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
        }
        return returnObject;
    }

    @RequestMapping("workbench/clue/deleteClueByIds.do")
    public @ResponseBody Object deleteClueByIds(String[] id){
        ReturnObject returnObject=new ReturnObject();
        try {
            int ret=clueService.deleteClueByIds(id);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
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

    @RequestMapping("workbench/clue/detailClue.do")
    public String detailClue(String id,HttpServletRequest request){
        Clue clue=clueService.selectClueForDetailById(id);
        List<ClueRemark> clueRemarkList=clueRemarkService.selectClueRemarkListById(id);
        List<Activity> activityList=clueService.selectClueActivityRelationByClueId(id);
        request.setAttribute("clue",clue);
        request.setAttribute("clueRemarkList",clueRemarkList);
        request.setAttribute("activityList",activityList);
        return "workbench/clue/detail";
    }


    @RequestMapping("workbench/clue/convert.do")
    public String convertClue(String id,HttpServletRequest request){
        Clue clue=clueService.selectClueForDetailById(id);
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        //List<ClueRemark> clueRemarkList=clueRemarkService.selectClueRemarkListById(id);
        //List<Activity> activityList=clueService.selectClueActivityRelationByClueId(id);
        request.setAttribute("clue",clue);
        request.setAttribute("stageList",stageList);
        //request.setAttribute("clueRemarkList",clueRemarkList);
        //request.setAttribute("activityList",activityList);
        return "workbench/clue/convert";
    }

    @RequestMapping("/workbench/clue/selectActivityForConvertByNameClueId.do")
    public @ResponseBody Object selectActivityForConvertByNameClueId(String activityName,String clueId){
        Map<String,Object> map=new HashMap();
        map.put("name",activityName);
        map.put("clueId",clueId);
        List<Activity> activityList=activityService.selectActivityForConvertByNameClueId(map);
        ReturnObject returnObject=new ReturnObject();
        if(activityList!=null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(activityList);
        }else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试！");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/clue/convertClue.do")
    public @ResponseBody Object convertClue(String clueId,String money,String name,String expectedData,String stage,String activityId,String isCreateTran,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        map.put("clueId",clueId);
        map.put("money",money);
        map.put("name",name);
        map.put("expectedData",expectedData);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("isCreateTran",isCreateTran);
        map.put(Contants.SESSION_USER,session.getAttribute(Contants.SESSION_USER));
        ReturnObject returnObject=new ReturnObject();
        try {
            clueService.saveConvertClue(map);

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试！");
        }
        return returnObject;
    }
}
