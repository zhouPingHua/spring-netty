package com.zph.demo.bean;


import com.zph.cralwer.principal.SimpleTicket;

/**
 * @author zph  on 2018/6/6
 */
public class CarrierTicket extends SimpleTicket {

    //用户ID
    private String userId;
    //手机号码
    private String phoneNo;
    //服务密码
    private String servicePwd;
    //客服密码
    private String customerServicePwd;
    //调用步骤方法
    private String method;
    //运营商标识 CM移动 CT电信 CU联通
    private String opCode;
    //省份标识 各省份拼音小写
    private String pCode;
    //图片验证码
    private String vCode;
    //短信验证码
    private String mCode;
    //是否第一次标识
    private String isFirst;
    //用户IP
    private String ip;

    private String errHtml;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getServicePwd() {
        return servicePwd;
    }

    public void setServicePwd(String servicePwd) {
        this.servicePwd = servicePwd;
    }

    public String getCustomerServicePwd() {
        return customerServicePwd;
    }

    public void setCustomerServicePwd(String customerServicePwd) {
        this.customerServicePwd = customerServicePwd;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getErrHtml() {
        return errHtml;
    }

    public void setErrHtml(String errHtml) {
        this.errHtml = errHtml;
    }

    @Override
    public String toString() {
        return "CarrierTicket{" +
                "userId=" + userId +
                ", phoneNo='" + phoneNo + '\'' +
                ", servicePwd='" + servicePwd + '\'' +
                ", customerServicePwd='" + customerServicePwd + '\'' +
                ", method='" + method + '\'' +
                ", opCode='" + opCode + '\'' +
                ", pCode='" + pCode + '\'' +
                ", vCode='" + vCode + '\'' +
                ", mCode='" + mCode + '\'' +
                ", isFirst='" + isFirst + '\'' +
                ", identify='" + identify + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }

}
