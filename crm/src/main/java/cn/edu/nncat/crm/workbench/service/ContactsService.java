package cn.edu.nncat.crm.workbench.service;

import cn.edu.nncat.crm.workbench.domain.Contacts;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-02 22:55
 * @version:1.0
 **/
public interface ContactsService {
    List<Contacts> selectContactsListByName(String fullname);
}
