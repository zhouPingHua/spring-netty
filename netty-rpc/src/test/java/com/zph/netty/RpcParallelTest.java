package com.zph.netty;

import com.zph.netty.client.RpcClient;
import com.zph.netty.registry.ServiceDiscovery;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author zph  on 2018/8/9
 */
public class RpcParallelTest {

    /*
    * 实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，
    * 将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，将其计数器初始化为1，
    * 多个线程在开始执行任务前首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒
    *
    *
    *某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为n new CountDownLatch(n) ，
    * 每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，当计数器的值变为0时，
    * 在CountDownLatch上 await() 的线程就会被唤醒。一个典型应用场景就是启动一个服务时，
    * 主线程需要等待多个组件加载完毕，之后再继续执行。
    *
    * */

    public static void main(String[] args) throws Exception {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("118.24.62.144:2181");
        final RpcClient executor = new RpcClient(serviceDiscovery);
        //并行度10000   CountDownLatch保证并行
        int parallel = 10000;

        //开始计时
        StopWatch sw = new StopWatch();
        sw.start();

        CountDownLatch signal = new CountDownLatch(1);
        CountDownLatch finish = new CountDownLatch(parallel);

        for (int index = 0; index < parallel; index++) {
            CalcParallelRequestThread client = new CalcParallelRequestThread(executor, signal, finish, index);
            new Thread(client).start();
        }

        //10000个并发线程瞬间发起请求操作
        signal.countDown();
        finish.await();

        sw.stop();

        String tip = String.format("RPC调用总共耗时: [%s] 毫秒", sw.getTime());
        System.out.println(tip);

        executor.stop();
    }
}
