package cn.edu.nncat.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-12 14:49
 * @version:1.0
 **/
@Controller
public class WorkbenchIndexController {
    @RequestMapping("/workbench/index.do")
    public String index(){
        return "workbench/index";
    }
}
