package cn.edu.nncat.crm.commons.utils;

import java.util.UUID;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-15 17:51
 * @version:1.0
 **/

public class UUIDUtils {

    /**
     *获取UUID的值
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}


