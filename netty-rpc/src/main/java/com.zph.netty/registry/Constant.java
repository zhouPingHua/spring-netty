package com.zph.netty.registry;

/**
 * @author zph  on 2018/8/8
 */
public interface Constant {

    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
    String ZK_TEST_PATH = ZK_REGISTRY_PATH + "/test";

}
