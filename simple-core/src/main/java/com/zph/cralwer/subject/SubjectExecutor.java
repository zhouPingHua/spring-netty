package com.zph.cralwer.subject;

import com.zph.cralwer.exception.CrawlerException;
import com.zph.cralwer.executor.Executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zph  on 2018/7/27
 */
public class SubjectExecutor {

    private Executor executor ;
    private int threadNum = 1;
    private List<String> methods = new ArrayList();

    public SubjectExecutor(Executor executor) {
        this.executor = executor;
    }

    public SubjectExecutor threadNum(int threadNum){
        this.threadNum = threadNum;
        return this;
    }

    public SubjectExecutor method(String... methods){
        this.methods.addAll(Arrays.asList(methods));
        return this;
    }

    public Object execute(){
        if(methods.size()==0) {
            throw CrawlerException.newInstance(653, "Parameter method must not be empty");
        }
        return executor.execute(this.methods.get(0));
    }

}
