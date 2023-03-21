package cn.edu.nncat.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-14 14:42
 * @version:1.0
 **/
@Controller
public class MainController {
    @RequestMapping("/workbench/main/index.do")
    public String index(){
        //跳转main/index.jsp
        return "workbench/main/index";
    }
}
