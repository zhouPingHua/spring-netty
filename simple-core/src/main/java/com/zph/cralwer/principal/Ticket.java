package com.zph.cralwer.principal;

import java.io.Serializable;

/**
 * @author zph  on 2018/7/26
 */
public interface Ticket extends Serializable {

    /**
     *  目标网站
     */
    String getDomain();

    /**
     *
     * @param domain
     */
    void setDomain(String domain);

    /**
     * 访问者标识
     * @return
     */
    String getIdentify();

    /**
     *
     * @param identify
     */
    void setIdentify(String identify);

}
