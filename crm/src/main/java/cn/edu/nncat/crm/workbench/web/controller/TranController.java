package cn.edu.nncat.crm.workbench.web.controller;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.domain.ReturnObject;
import cn.edu.nncat.crm.settings.domain.DicValue;
import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.settings.service.DicValueService;
import cn.edu.nncat.crm.settings.service.UserService;
import cn.edu.nncat.crm.workbench.domain.*;
import cn.edu.nncat.crm.workbench.mapper.CustomerMapper;
import cn.edu.nncat.crm.workbench.mapper.TranHistoryMapper;
import cn.edu.nncat.crm.workbench.service.*;
import org.apache.poi.hssf.record.SSTRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-12-08 09:34
 * @version:1.0
 **/
@Controller
public class TranController {


    @Autowired
    private TranRemarkService tranRemarkService;

    @Autowired
    private TranHistoryService tranHistoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private TranService tranService;

    @Autowired
    private UserService userService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/transaction/index.do")
    public String index(HttpServletRequest request){
        List<Tran> tranList=tranService.selectTranByList();
        request.setAttribute("tranList",tranList);
        return "/workbench/transaction/index";
    }

    @RequestMapping("/workbench/transaction/save.do")
    public String save(HttpServletRequest request){
        List<User> userList=userService.queryAllUsers();
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        request.setAttribute("userList",userList);
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);
        return "/workbench/transaction/save";
    }

    @RequestMapping("/workbench/transaction/searchActivity.do")
    public @ResponseBody Object selectActivityListByName(String name){
        List<Activity> activityList=activityService.selectActivityListForAll(name);
        ReturnObject returnObject =new ReturnObject();
        if(activityList!=null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(activityList);
        }else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙请稍后重试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/transaction/searchContacts.do")
    public @ResponseBody Object selectContactsListByName(String fullname){
        List<Contacts> contactsList=contactsService.selectContactsListByName(fullname);
        ReturnObject returnObject =new ReturnObject();
        if(contactsList!=null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(contactsList);
        }else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙请稍后重试。。。");
        }
        return returnObject;
    }


    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    public @ResponseBody Object getPossibilityByStage(String stageValue){
        ResourceBundle bundle = ResourceBundle.getBundle("possibility");
        String possibility = bundle.getString(stageValue);
      /*  ReturnObject returnObject=new ReturnObject();
        if (possibility!=null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(possibility);
        }else{
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);

        }*/
        return possibility;
    }

    @RequestMapping("/workbench/transaction/queryCustomerNameByName.do")
    public @ResponseBody Object queryCustomerNameByName(String customerName){
        List<String> customerNameList=customerService.queryCustomerNameByName(customerName);
        return customerNameList;
    }

    @RequestMapping("/workbench/transaction/saveCreateTran.do")
    public @ResponseBody Object saveCreateTran(@RequestParam Map<String,Object> map, HttpSession session){
        map.put(Contants.SESSION_USER,session.getAttribute(Contants.SESSION_USER));
        ReturnObject returnObject=new ReturnObject();
        try {
            tranService.saveCreateTran(map);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/transaction/detail.do")
    public String tranDetail(String tranId, HttpServletRequest request){
        Tran tran=tranService.selectTranById(tranId);
        List<TranRemark> tranRemarkList=tranRemarkService.selectTranRemarkListByTranId(tranId);
        List<TranHistory> tranHistoryList=tranHistoryService.selectTranHistoryListByTranId(tranId);
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        ResourceBundle resourceBundle=ResourceBundle.getBundle("possibility");
        String possibility=resourceBundle.getString(tran.getStage());
        tran.setPossibility(possibility);
        request.setAttribute("tran",tran);
        request.setAttribute("tranRemarkList",tranRemarkList);
        request.setAttribute("tranHistoryList",tranHistoryList);
        request.setAttribute("stageList",stageList);
        //这段不用会导致request太多，直接在tran扩展属性request.setAttribute("possibility",possibility);

        return "workbench/transaction/detail";
    }
}
