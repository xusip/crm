package cn.edu.nncat.crm.settings.service;

import cn.edu.nncat.crm.settings.domain.DicValue;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-17 21:39
 * @version:1.0
 **/
public interface DicValueService {
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
