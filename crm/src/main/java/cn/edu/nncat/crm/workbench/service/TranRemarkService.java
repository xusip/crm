package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.TranRemark;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-11 21:06
 * @version:1.0
 **/
public interface TranRemarkService {
    List<TranRemark> selectTranRemarkListByTranId(String tranId);
}
