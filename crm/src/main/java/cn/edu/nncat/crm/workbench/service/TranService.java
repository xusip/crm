package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.FunnelVO;
import cn.edu.nncat.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-12-08 09:10
 * @version:1.0
 **/
public interface TranService {
    List<Tran> selectTranByList();
    void saveCreateTran(Map<String,Object> map);
    Tran selectTranById(String tranId);
    List<FunnelVO> selectCountOfTranGroupByStage();
}
