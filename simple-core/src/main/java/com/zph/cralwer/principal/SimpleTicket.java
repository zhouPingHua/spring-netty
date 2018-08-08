package com.zph.cralwer.principal;

/**
 * @author zph  on 2018/7/26
 */
public class SimpleTicket implements Ticket{

    protected String domain;
    protected String identify;

    @Override
    public String getIdentify() {
        return identify;
    }

    @Override
    public void setIdentify(String identify) {
        this.identify =identify;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public void setDomain(String domain) {
        this.domain = domain;
    }
}
