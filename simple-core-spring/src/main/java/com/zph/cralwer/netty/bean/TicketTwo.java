package com.zph.cralwer.netty.bean;

import com.zph.cralwer.principal.SimpleTicket;

/**
 * @author zph  on 2018/8/6
 */
public class TicketTwo extends SimpleTicket {

    //用户ID
    private String userId;
    //手机号码
    private String phoneNo;


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
}
