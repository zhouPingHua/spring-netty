package com.zph.netty.client;

import com.zph.netty.protocol.RpcRequest;
import com.zph.netty.protocol.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zph  on 2018/8/8
 */
public class MessageCallBack {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageCallBack.class);

    private RpcRequest request;
    private RpcResponse response;
    private long startTime;
    private final int calltimeOut = 10*1000;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public MessageCallBack(RpcRequest request) {
        this.request = request;
        this.startTime = System.currentTimeMillis();
    }

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            //设定一下超时时间，rpc服务器太久没有响应的话，就默认返回空吧。
            //当执行await方法时，线程将释放锁，并且将自己沉睡，等待唤醒
            finish.await(calltimeOut, TimeUnit.MILLISECONDS);
            if (this.response != null) {
                return this.response.getResult();
            } else {
                return null;
            }
        } catch (RuntimeException e){
            throw new RuntimeException("Timeout exception. Request id: " + this.request.getRequestId()
                    + ". Request class name: " + this.request.getClassName()
                    + ". Request method: " + this.request.getMethodName());
        }finally {
            lock.unlock();
        }
    }

    public void over(RpcResponse reponse) {
        try {
            lock.lock();
            finish.signal();
            this.response = reponse;
            long responseTime = System.currentTimeMillis() - startTime;
            LOGGER.info("service response time is "+responseTime);
            if (responseTime > this.calltimeOut) {
                LOGGER.warn("Service response time is too slow. Request id = " + reponse.getRequestId() + ". Response Time = " + responseTime + "ms");
            }
        } finally {
            lock.unlock();
        }
    }
}
