package cn.edu.nncat.crm.settings.web.controller;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.commons.domain.ReturnObject;
import cn.edu.nncat.crm.commons.utils.DateUtils;
import cn.edu.nncat.crm.settings.domain.User;
import cn.edu.nncat.crm.settings.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-10 11:10
 * @version:1.0
 **/
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {

        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        map.put("isRemPwd",isRemPwd);
        //调用service方法，查询用户
        User user = userService.queryUserByLoginActAndPwd(map);
        ReturnObject returnObject = new ReturnObject();
        //根据查询结果，生成相应信息
        if(user==null){
            //登录失败，用户名密码错误
            returnObject.setCode("0");
            returnObject.setMessage("用户名或密码错误");
        }else {
            //进一步判断账号是否合法
            if(DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //登录失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            }else if ("0".equals(user.getLockState())){
                //登录失败，状态已锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            }else if (!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else{
                //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                session.setAttribute(Contants.SESSION_USER,user);

                //如果需要记住密码，则往外写cookie
                if("true".equals(isRemPwd)){
                    System.out.println("isRemPwd------------"+isRemPwd);
                   Cookie c1 = new Cookie("loginAct",user.getLoginAct());
                   c1.setMaxAge(10*24*60*60);
                   response.addCookie(c1);
                   Cookie c2 = new Cookie("loginPwd",user.getLoginPwd());
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else {
                    System.out.println("isRemPwd------------"+isRemPwd);
                    //把没有过期的Cookie删除
                    Cookie c1 = new Cookie("loginAct","1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd","1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }
            }
        }
        System.out.println("-------------------------------"+returnObject.toString());
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空Cookie
        Cookie c1 = new Cookie("loginAct","1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        session.invalidate();
        //跳转首页
        return "redirect:/";
    }

}
