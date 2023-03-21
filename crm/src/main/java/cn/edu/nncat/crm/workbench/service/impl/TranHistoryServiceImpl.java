package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.workbench.domain.TranHistory;
import cn.edu.nncat.crm.workbench.mapper.TranHistoryMapper;
import cn.edu.nncat.crm.workbench.service.TranHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-11 20:54
 * @version:1.0
 **/
@Service("tranHistoryService")
public class TranHistoryServiceImpl implements TranHistoryService {

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public List<TranHistory> selectTranHistoryListByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryListByTranId(tranId);
    }
}
