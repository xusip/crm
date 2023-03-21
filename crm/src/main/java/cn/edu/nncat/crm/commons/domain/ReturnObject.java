package cn.edu.nncat.crm.commons.domain;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-12 11:57
 * @version:1.0
 **/
public class ReturnObject {
    private String code;    //提示成功或失败的标志，1---成功，0---失败
    private  String message;   //提示信息
    private  Object retData;    //其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "ReturnObject{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", retData=" + retData +
                '}';
    }
}
