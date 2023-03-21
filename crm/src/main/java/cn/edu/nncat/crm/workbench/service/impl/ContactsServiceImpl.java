package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.workbench.domain.Contacts;
import cn.edu.nncat.crm.workbench.mapper.ContactsMapper;
import cn.edu.nncat.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-02 22:57
 * @version:1.0
 **/
@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public List<Contacts> selectContactsListByName(String fullname) {
        return contactsMapper.selectContactsListByName(fullname);
    }
}
