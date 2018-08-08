package com.zph.demo.config;

import com.zph.cralwer.SimpleCrawler;
import com.zph.demo.bean.CarrierTicket;

/**
 * @author zph  on 2018/7/27
 */
public class CarrierCrawler extends SimpleCrawler<CarrierSubject,CarrierTicket> {

    @Override
    public CarrierSubject getSubject(CarrierTicket token) {
        return new CarrierSubject(token, processorRegister);
    }
}