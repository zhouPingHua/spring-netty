package com.zph.cralwer;

import com.zph.cralwer.principal.Ticket;
import com.zph.cralwer.subject.Subject;

/**
 * @author zph  on 2018/7/27
 */
public interface Crawler <S extends Subject,T extends Ticket> {

    S getSubject(T ticket);
}
