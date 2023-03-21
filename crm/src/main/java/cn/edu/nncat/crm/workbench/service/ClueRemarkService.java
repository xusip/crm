package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-11-07 19:01
 * @version:1.0
 **/
public interface ClueRemarkService {
    int insertClueRemark(ClueRemark clueRemark);
    List<ClueRemark> selectClueRemarkListById(String id);
    int updateClueRemarkById(ClueRemark clueRemark);
    int deleteClueRemarkById(String id);
}
