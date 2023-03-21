package cn.edu.nncat.crm.workbench.web.controller;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.domain.ReturnObject;
import cn.edu.nncat.crm.commons.utils.DateUtils;
import cn.edu.nncat.crm.commons.utils.HSSFUtils;
import cn.edu.nncat.crm.commons.utils.UUIDUtils;
import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.settings.service.UserService;
import cn.edu.nncat.crm.workbench.domain.Activity;
import cn.edu.nncat.crm.workbench.domain.ActivityRemark;
import cn.edu.nncat.crm.workbench.service.ActivityRemarkService;
import cn.edu.nncat.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-15 10:57
 * @version:1.0
 **/
@Controller
public class ActivityController {
    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法查询所有用户
        List<User> userList=userService.queryAllUsers();
        //把数据保存到request中
        request.setAttribute("userList",userList);
        //请求转发到市场活动的主页面
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。");
            }

        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate,int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Activity> activityList= activityService.queryActivityByConditionForPage(map);
        int totalRows= activityService.queryCountOfActivityByCondition(map);
        //根据查询结果，生成响应信息
        Map<String,Object> retMap=new HashMap<>();
        retMap.put("activityList",activityList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityIds.do")
    public @ResponseBody Object deleteActivityIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret= activityService.deleteActivityByIds(id);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/selectActivityById.do")
    public @ResponseBody Object selectActivityById(String id){
        Activity activity= activityService.selectActivityById(id);
        return activity;
    }

    @RequestMapping("/workbench/activity/updateActivityById.do")
    public @ResponseBody Object updateActivityById(Activity activity,HttpSession session){
        selectActivityById(activity.getId());
        ReturnObject returnObject=new ReturnObject();
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        //activity.setId(activity.getId());
        activity.setEditTime(DateUtils.formateDateTime(new Date()));
        activity.setEditBy(user.getId());
        try{
            int ret= activityService.updateActivityById(activity);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream;charset=UTF-8");

        response.addHeader("Content-Disposition","attachment;filename=mystudentList.xls");

        OutputStream out=response.getOutputStream();
        InputStream is=new FileInputStream("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\studentList.xls");
        byte[] bytes=new byte[256];
        int len=0;
        while ((len=is.read(bytes))!=-1){
            out.write(bytes,0,len);
        }
        is.close();
        out.flush();
    }

    @RequestMapping("/workbench/activity/exportAllActivitys.do")
    public void exportAllActivitys(HttpServletResponse response) throws Exception{
        List<Activity> activityList= activityService.queryAllActivitys();
        HSSFWorkbook wb=new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("市场活动列表");
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell=row.createCell(0);
        cell.setCellValue("ID");
        cell=row.createCell(1);
        cell.setCellValue("所有者");
        cell=row.createCell(2);
        cell.setCellValue("名称");
        cell=row.createCell(3);
        cell.setCellValue("开始日期");
        cell=row.createCell(4);
        cell.setCellValue("结束日期");
        cell=row.createCell(5);
        cell.setCellValue("成本");
        cell=row.createCell(6);
        cell.setCellValue("描述");
        cell=row.createCell(7);
        cell.setCellValue("创建时间");
        cell=row.createCell(8);
        cell.setCellValue("创建者");
        cell=row.createCell(9);
        cell.setCellValue("修改时间");
        cell=row.createCell(10);
        cell.setCellValue("修改者");

        if(activityList!=null&&activityList.size()>0){
            Activity activity=null;
            for(int i=0;i<activityList.size();i++){
                activity=activityList.get(i);
                row=sheet.createRow(i+1);
                cell=row.createCell(0);
                cell.setCellValue(activity.getId());
                cell=row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell=row.createCell(2);
                cell.setCellValue(activity.getName());
                cell=row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell=row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell=row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell=row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell=row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell=row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell=row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell=row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
      /*  FileOutputStream os = new FileOutputStream("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\activityList.xls");
        wb.write(os);
        os.close();
        wb.close();*/

        response.setContentType("application/octet-stream;charset=UTF-8");

        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");

        OutputStream out=response.getOutputStream();
       /* InputStream is=new FileInputStream("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\activityList.xls");
        byte[] bytes=new byte[256];
        int len=0;
        while ((len=is.read(bytes))!=-1){
            out.write(bytes,0,len);
        }*/
       wb.write(out);
        //is.close();
        out.flush();
    }

    /*
    * 反例：
    * */
    @RequestMapping("/workbench/activity/fileUpload.do")
    public @ResponseBody Object fileUpload(String userName, MultipartFile myFile) throws Exception{
        System.out.print("userName="+userName);
        String originalFilename=myFile.getOriginalFilename();
        File file = new File("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\"+originalFilename);
        myFile.transferTo(file);
        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("上传成功！");
        return returnObject;
    }

    @RequestMapping("/workbench/activity/importActivity.do")
    public @ResponseBody Object importActivity(MultipartFile activityFile,HttpSession session){
        User user =(User)session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        try{
            //把Excel文件写在磁盘目录下
            /*String originalFilename=activityFile.getOriginalFilename();
            File file = new File("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\"+originalFilename);
            activityFile.transferTo(file);*/

            //InputStream is = new FileInputStream("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\"+originalFilename);
            InputStream is = activityFile.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;
            List<Activity> activityList=new ArrayList<>();
            for (int i = 1; i <=sheet.getLastRowNum() ; i++) {
                    row=sheet.getRow(i);
                    activity = new Activity();
                    activity.setId(UUIDUtils.getUUID());
                    activity.setOwner(user.getId());
                    activity.setCreateTime(DateUtils.formateDateTime(new Date()));
                    activity.setCreateBy(user.getId());
                    for (int j=0;j<row.getLastCellNum();j++){
                        cell=row.getCell(j);
                        String StringValue = HSSFUtils.getCellValueForStr(cell);
                        if(j==0){
                            activity.setName(StringValue);
                        }else if (j==1){
                            activity.setStartDate(StringValue);
                        }else if (j==2){
                            activity.setEndDate(StringValue);
                        }else if (j==3){
                            activity.setCost(StringValue);
                        }else if (j==4){
                            activity.setDescription(StringValue);
                        }
                    }
                    activityList.add(activity);
                System.out.println("========================="+activity.toString());
            }

           int ret = activityService.saveCreateActivityByList(activityList);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(ret);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
       Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList =activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        return "workbench/activity/detail";
    }
}
