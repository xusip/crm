package cn.edu.nncat.crm.settings.service;

import cn.edu.nncat.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-11 16:19
 * @version:1.0
 **/
public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);
    List<User> queryAllUsers();
}
