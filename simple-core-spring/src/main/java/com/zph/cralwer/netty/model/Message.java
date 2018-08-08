package com.zph.cralwer.netty.model;

import com.zph.cralwer.principal.SimpleTicket;

/**
 * @author zph  on 2018/8/6
 */
public class Message extends SimpleTicket {

    private static final long serialVersionUID = -7543514952950971498L;
    private String id;
    private String content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
