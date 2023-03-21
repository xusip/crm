package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.workbench.mapper.CustomerMapper;
import cn.edu.nncat.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2023-03-09 21:10
 * @version:1.0
 **/
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<String> queryCustomerNameByName(String customerName) {
        return customerMapper.queryCustomerNameByName(customerName);
    }
}
