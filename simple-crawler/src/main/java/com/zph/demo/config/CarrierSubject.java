package com.zph.demo.config;

import com.zph.cralwer.principal.Ticket;
import com.zph.cralwer.processor.ProcessorRegister;
import com.zph.cralwer.subject.SimpleSubject;
import com.zph.demo.bean.CarrierTicket;

/**
 * @author zph  on 2018/7/27
 */
public class CarrierSubject  extends SimpleSubject {

    public CarrierSubject(Ticket ticket, ProcessorRegister processorRegister) {
        super(ticket, processorRegister);
    }

    /**
     * 从ticket中获取执行的方法目标，并执行
     * @return
     */
    public Object doWork() {
        CarrierTicket ticket = (CarrierTicket) this.getTicket();
        return this.executor().method(ticket.getMethod()).execute();
    }

}