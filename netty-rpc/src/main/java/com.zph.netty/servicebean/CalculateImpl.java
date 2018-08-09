package com.zph.netty.servicebean;

import com.zph.netty.server.RpcService;

/**
 * @author zph  on 2018/8/9
 */
@RpcService(Calculate.class)
public class CalculateImpl implements Calculate {
    //两数相加
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
