package cn.edu.nncat.crm.workbench.service.impl;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.utils.DateUtils;
import cn.edu.nncat.crm.commons.utils.UUIDUtils;
import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.workbench.domain.Customer;
import cn.edu.nncat.crm.workbench.domain.FunnelVO;
import cn.edu.nncat.crm.workbench.domain.Tran;
import cn.edu.nncat.crm.workbench.domain.TranHistory;
import cn.edu.nncat.crm.workbench.mapper.CustomerMapper;
import cn.edu.nncat.crm.workbench.mapper.TranHistoryMapper;
import cn.edu.nncat.crm.workbench.mapper.TranMapper;
import cn.edu.nncat.crm.workbench.service.TranService;
import com.sun.tools.corba.se.idl.constExpr.ShiftRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-12-08 09:11
 * @version:1.0
 **/
@Service("tranService")
public class TranServiceImpl implements TranService {

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TranMapper tranMapper;

    @Override
    public List<Tran> selectTranByList() {
        return tranMapper.selectTranByList();
    }

    @Override
    public void saveCreateTran(Map<String, Object> map) {
        String customerName=(String)map.get("customerName");
        User user=(User)map.get(Contants.SESSION_USER);
        Customer customer=customerMapper.selectCustomerByName(customerName);
        if(customer==null){
            customer=new Customer();
            customer.setOwner(user.getId());
            customer.setName(customerName);
            customer.setId(UUIDUtils.getUUID());
            customer.setCreateTime(DateUtils.formateDateTime(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);
        }

        Tran tran=new Tran();
        tran.setOwner((String)map.get("owner"));
        tran.setStage((String)map.get("stage"));
        tran.setNextContactTime((String)map.get("nextContactTime"));
        tran.setName((String)map.get("name"));
        tran.setMoney((String)map.get("money"));
        tran.setId(UUIDUtils.getUUID());
        tran.setExpectedDate((String)map.get("expectedDate"));
        tran.setCustomerId(customer.getId());
        tran.setCreateTime(DateUtils.formateDateTime(new Date()));
        tran.setCreateBy(user.getId());
        tran.setContactSummary((String)map.get("contactSummary"));
        tran.setContactsId((String)map.get("contactsId"));
        tran.setActivityId((String)map.get("activityId"));
        tran.setDescription((String)map.get("description"));
        tran.setSource((String)map.get("source"));
        tran.setType((String)map.get("type"));
        tranMapper.insertTran(tran);

        //保存交易历史
        TranHistory tranHistory=new TranHistory();
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtils.formateDateTime(new Date()));
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistoryMapper.insertTranHistory(tranHistory);
    }

    @Override
    public Tran selectTranById(String tranId) {
        return tranMapper.selectTranById(tranId);
    }

    @Override
    public List<FunnelVO> selectCountOfTranGroupByStage() {
        return tranMapper.selectCountOfTranGroupByStage();
    }
}
