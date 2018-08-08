package com.zph.cralwer.processor;

import com.zph.cralwer.principal.Ticket;

/**
 * @author zph  on 2018/7/26
 */
public interface PageProcessor {

    Object process(Ticket ticket);

}
