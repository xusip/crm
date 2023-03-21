package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.Activity;
import cn.edu.nncat.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-18 14:40
 * @version:1.0
 **/
public interface ClueService {
    int saveCreateClue(Clue clue);
    List<Clue> queryClueByConditionForPage(Map<String,Object> map);
    int queryCountOfClueByCondition(Map<String,Object> map);
    int updateClueById(Clue clue);
    Clue selectClueByPrimaryKey(String id);
    int deleteClueByIds(String[] id);
    Clue selectClueForDetailById(String id);
    List<Activity> selectClueActivityRelationByClueId(String clueId);
    void saveConvertClue(Map<String,Object> map);
}
