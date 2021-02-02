package com.ruoyi.buyer.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author ztt
 */
public class OrderEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private Object msg;

    public OrderEvent(Object source, Object msg) {
        super(source);
        this.msg = msg;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
