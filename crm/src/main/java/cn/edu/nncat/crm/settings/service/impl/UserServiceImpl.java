package cn.edu.nncat.crm.settings.service.impl;

import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.settings.mapper.UserMapper;
import cn.edu.nncat.crm.settings.service.DicValueService;
import cn.edu.nncat.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-11 16:22
 * @version:1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }

}
