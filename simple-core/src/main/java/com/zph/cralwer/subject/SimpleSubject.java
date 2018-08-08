package com.zph.cralwer.subject;

import com.zph.cralwer.exception.CrawlerException;
import com.zph.cralwer.executor.Executor;
import com.zph.cralwer.principal.Ticket;
import com.zph.cralwer.processor.PageProcessor;
import com.zph.cralwer.processor.ProcessorRegister;

import java.util.Map;

/**
 * @author zph  on 2018/7/27
 */
public class SimpleSubject implements Subject {

    /**
     * 执行器
     */
    private Executor executor;
    /**
     * 访问者token
     */
    private Ticket ticket;

    public SimpleSubject(Ticket ticket, ProcessorRegister processorRegistertor) {
        this.ticket = ticket;
        init(processorRegistertor);
    }

    void init(ProcessorRegister processorRegister){
        if(ticket.getDomain() == null){
            throw CrawlerException.newInstance(656,"The 'domain' parameter cannot be empty!");
        }
        applyTicketAware();
        initExecutor(processorRegister);
    }



    /**
     * 使用Aware模式给访问器注入ticket
     */
    private void applyTicketAware() {

    }

    private void initExecutor(ProcessorRegister processorRegister) {
        this.executor = new Executor(initProcess(processorRegister),ticket);
    }

    /**
     * 初始化处理器集合
     * 从处理器注册中心获取当前domain下注册的处理器
     * @param processorRegister
     * @return
     */
    private Map<String,PageProcessor> initProcess(ProcessorRegister processorRegister){
        Map<String,PageProcessor> processors = processorRegister.load(ticket.getDomain());
        if(processors == null){
            throw CrawlerException.newInstance(655,"The processor doesn't exist! domain : %s" , ticket.getDomain());
        }
        return processors;
    }


    @Override
    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public SubjectExecutor executor() {
        return new SubjectExecutor(executor);
    }
}
