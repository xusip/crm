package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.TranHistory;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-11 20:51
 * @version:1.0
 **/
public interface TranHistoryService {
    List<TranHistory> selectTranHistoryListByTranId(String tranId);
}
