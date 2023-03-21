package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.workbench.domain.TranRemark;
import cn.edu.nncat.crm.workbench.mapper.TranRemarkMapper;
import cn.edu.nncat.crm.workbench.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-11 21:07
 * @version:1.0
 **/
@Service("tranRemarkService")
public class TranRemarkServiceImpl implements TranRemarkService {
    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Override
    public List<TranRemark> selectTranRemarkListByTranId(String tranId) {
        return tranRemarkMapper.selectTranRemarkListByTranId(tranId);
    }
}
