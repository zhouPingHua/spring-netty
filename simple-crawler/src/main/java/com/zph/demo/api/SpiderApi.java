package com.zph.demo.api;

import com.alibaba.fastjson.JSONObject;
import com.zph.cralwer.netty.annotation.RequestMethod;
import com.zph.cralwer.netty.annotation.Spider;
import com.zph.cralwer.netty.bean.TicketTwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zph  on 2018/8/6
 */
@Spider("/zph/demo")
public class SpiderApi {

    Logger logger = LoggerFactory.getLogger(SpiderApi.class);

    private AtomicInteger threadAlive = new AtomicInteger();

    @RequestMethod("/test")
    public String spider(TicketTwo ticket) {
        logger.info("import spider======"+threadAlive.get());
        threadAlive.incrementAndGet();
        return JSONObject.toJSONString(ticket);
    }


    @RequestMethod("/test2")
    public String spider2(TicketTwo ticket) {
        return JSONObject.toJSONString(ticket);
    }
}
