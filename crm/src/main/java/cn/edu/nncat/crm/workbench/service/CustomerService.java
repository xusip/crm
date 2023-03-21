package cn.edu.nncat.crm.workbench.service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-09 21:08
 * @version:1.0
 **/
public interface CustomerService {
    List<String> queryCustomerNameByName(String customerName);
}
