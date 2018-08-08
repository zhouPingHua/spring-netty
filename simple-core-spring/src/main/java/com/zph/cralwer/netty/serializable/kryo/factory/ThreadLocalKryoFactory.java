package com.zph.cralwer.netty.serializable.kryo.factory;

import com.esotericsoftware.kryo.Kryo;

/**
 * @author zph  on 2018/8/6
 */
public class ThreadLocalKryoFactory extends KryoFactory {

    private final ThreadLocal<Kryo> holder = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            return createKryo();
        }
    };

    public Kryo getKryo() {
        return holder.get();
    }
}
