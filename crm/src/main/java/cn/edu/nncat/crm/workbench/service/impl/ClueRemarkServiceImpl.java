package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.workbench.domain.ClueRemark;
import cn.edu.nncat.crm.workbench.mapper.ClueRemarkMapper;
import cn.edu.nncat.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-11-07 19:02
 * @version:1.0
 **/
@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public int insertClueRemark(ClueRemark clueRemark) {
        return clueRemarkMapper.insertClueRemark(clueRemark);
    }

    @Override
    public List<ClueRemark> selectClueRemarkListById(String id) {
        return clueRemarkMapper.selectClueRemarkListById(id);
    }

    @Override
    public int updateClueRemarkById(ClueRemark clueRemark) {
        return clueRemarkMapper.updateClueRemarkById(clueRemark);
    }

    @Override
    public int deleteClueRemarkById(String id) {
        return clueRemarkMapper.deleteClueRemarkById(id);
    }
}
