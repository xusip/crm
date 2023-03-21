package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.workbench.domain.ClueActivityRelation;
import cn.edu.nncat.crm.workbench.mapper.ClueActivityRelationMapper;
import cn.edu.nncat.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-11-17 21:40
 * @version:1.0
 **/
@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    /*@Override
    public int saveClueActivityRelationByActId(ClueActivityRelation clueActivityRelation) {
        return clueActivityRelationMapper.insertClueActivityRelationByActId(clueActivityRelation);
    }*/

    @Override
    public int saveClueActivityRelationByList(List<ClueActivityRelation> clueActivityRelationList) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(clueActivityRelationList);
    }

    @Override
    public int deleteClueActivityRelationByClueIdActId(ClueActivityRelation clueActivityRelation) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueIdActId(clueActivityRelation);
    }
}
