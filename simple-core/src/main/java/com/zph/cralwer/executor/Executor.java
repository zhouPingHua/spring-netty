package com.zph.cralwer.executor;

import com.zph.cralwer.exception.CrawlerException;
import com.zph.cralwer.principal.Ticket;
import com.zph.cralwer.processor.PageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zph  on 2018/7/27
 */
public class Executor {

    private static final Logger logger = LoggerFactory.getLogger(Executor.class);

    private Ticket ticket;

    /**
     * 处理器集合
     */
    private Map<String,PageProcessor> processors = new HashMap();


    public Executor(Map<String,PageProcessor> processors,Ticket ticket) {
        this.processors = processors;
        this.ticket = ticket;
    }


    /**
     * 执行处理器
     * @param method
     * @return
     */
    private Object doProcessor(String method){
        if(method == null) {
            throw CrawlerException.newInstance(653, "Parameter methods must not be empty");
        }
        PageProcessor pageProcessor = processors.get(method);
        if(pageProcessor!=null){
            return pageProcessor.process(ticket);
        }else {
            throw CrawlerException.newInstance(654,"processor %s.%s not exist",ticket.getDomain(),method);
        }
    }



    /**
     * 同步执行 单次只可执行单个processor  processor执行后会依次执行pipelines
     * @param method
     * @return
     */
    public Object execute(String method){
        Object result = doProcessor(method);
        return result;
    }


    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Map<String, PageProcessor> getProcessors() {
        return processors;
    }

    public void setProcessors(Map<String, PageProcessor> processors) {
        this.processors = processors;
    }
}
