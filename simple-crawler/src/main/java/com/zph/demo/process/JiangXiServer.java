package com.zph.demo.process;

import com.alibaba.fastjson.JSONObject;
import com.zph.cralwer.annotation.Process;
import com.zph.cralwer.principal.Ticket;
import com.zph.cralwer.processor.PageProcessor;
import com.zph.demo.bean.CarrierTicket;

/**
 * @author zph  on 2018/7/27
 */
@Process(method = "test" , domain = "CMjiangxi")
public class JiangXiServer implements PageProcessor{
    @Override
    public Object process(Ticket ticket) {
        CarrierTicket carrierTicket = new CarrierTicket();
        carrierTicket.setMethod("test");
        carrierTicket.setpCode("tset");
        return JSONObject.parseObject(JSONObject.toJSONString(carrierTicket));
    }
}
