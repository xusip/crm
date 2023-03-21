package cn.edu.nncat.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-09 09:00
 * @version:1.0
 **/
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
