package cn.smart.support.qiniu;

import java.util.List;

public class QiniuRet {

    private int code;
    private String msg;
    private Object object;
    private List errorList;

    QiniuRet() {
        this.code = 200;
    }

    QiniuRet(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List getErrorList() {
        return errorList;
    }

    public void setErrorList(List errorList) {
        this.errorList = errorList;
    }

    public List getSuccessList() {
        return successList;
    }

    public void setSuccessList(List successList) {
        this.successList = successList;
    }

    private List successList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
