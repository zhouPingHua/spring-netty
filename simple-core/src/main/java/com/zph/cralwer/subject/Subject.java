package com.zph.cralwer.subject;

import com.zph.cralwer.principal.Ticket;

import java.io.Serializable;

/**
 * @author zph  on 2018/7/27
 */
public interface Subject extends Serializable {

    /**
     * 身份票据
     * @return
     */
    Ticket getTicket();

    /**
     * 执行器
     * @return
     */
    SubjectExecutor executor();
}
