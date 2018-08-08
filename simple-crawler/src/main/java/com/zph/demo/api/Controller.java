package com.zph.demo.api;

import com.alibaba.fastjson.JSONObject;
import com.zph.demo.bean.CarrierTicket;
import com.zph.demo.config.CarrierCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zph  on 2018/7/27
 */
@RestController
public class Controller {

    @Autowired
    private CarrierCrawler carrierCrawler;

    Logger logger = LoggerFactory.getLogger(SpiderApi.class);

    private AtomicInteger threadAlive = new AtomicInteger();

    @RequestMapping(value = "/zph/demo/test",method = RequestMethod.GET)
    String spider(CarrierTicket ticket) {
        logger.info("import spider======"+threadAlive.get());
        threadAlive.incrementAndGet();
        return JSONObject.toJSONString(ticket);
//        JSONObject jsonObject = (JSONObject) carrierCrawler.getSubject(ticket).doWork();
//        return jsonObject.toString();
    }




}
