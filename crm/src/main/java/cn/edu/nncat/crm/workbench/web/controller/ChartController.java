package cn.edu.nncat.crm.workbench.web.controller;

import cn.edu.nncat.crm.workbench.domain.FunnelVO;
import cn.edu.nncat.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-13 22:19
 * @version:1.0
 **/
@Controller
public class ChartController {

    @Autowired
    private TranService tranService;

    @RequestMapping("/workbench/chart/transaction/index.do")
    public String index(){
        return "workbench/chart/transaction/index";
    }

    @RequestMapping("/workbench/chart/transaction/selectCountOfTranGroupByStage.do")
    public @ResponseBody Object selectCountOfTranGroupByStage(){
        List<FunnelVO> funnelVOList=tranService.selectCountOfTranGroupByStage();
        return funnelVOList;
    }
}
