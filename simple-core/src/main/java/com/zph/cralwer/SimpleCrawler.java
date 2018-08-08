package com.zph.cralwer;

import com.zph.cralwer.principal.Ticket;
import com.zph.cralwer.processor.ProcessorRegister;
import com.zph.cralwer.subject.Subject;

/**
 * @author zph  on 2018/7/27
 */
public abstract class SimpleCrawler<S extends Subject,T extends Ticket> implements Crawler<S,T> {

    protected ProcessorRegister processorRegister;

    public SimpleCrawler(){};

    public SimpleCrawler(ProcessorRegister processorRegister){
        this();
        this.processorRegister = processorRegister;
    }

    public ProcessorRegister getProcessorRegister() {
        return processorRegister;
    }

    public void setProcessorRegister(ProcessorRegister processorRegister) {
        this.processorRegister = processorRegister;
    }
}
