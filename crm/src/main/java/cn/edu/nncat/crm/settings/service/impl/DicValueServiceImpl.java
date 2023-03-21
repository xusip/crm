package cn.edu.nncat.crm.settings.service.impl;

import cn.edu.nncat.crm.settings.domain.DicValue;
import cn.edu.nncat.crm.settings.mapper.DicValueMapper;
import cn.edu.nncat.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-17 21:41
 * @version:1.0
 **/
@Service("DicValueService")
public class DicValueServiceImpl implements DicValueService {
    @Autowired
    private DicValueMapper dicValueMapper;
    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
