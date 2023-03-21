package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-11-17 21:38
 * @version:1.0
 **/
public interface ClueActivityRelationService {

    /* 自己写的
    int saveClueActivityRelationByActId(ClueActivityRelation clueActivityRelation);*/
    int saveClueActivityRelationByList(List<ClueActivityRelation> clueActivityRelationList);
    int deleteClueActivityRelationByClueIdActId(ClueActivityRelation clueActivityRelation);
}
