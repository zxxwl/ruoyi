//package com.ruoyi.buyer.listener;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.scheduling.annotation.Async;
//
///**
// * 监听配置类
// * @author oKong
// */
//@Configuration
//@Slf4j
//public class EventListenerConfig {
//    @EventListener
//    public void handleEvent(Object event) {
//        //监听所有事件 可以看看 系统各类时间 发布了哪些事件
//        //可根据 instanceof 监听想要监听的事件
////        if(event instanceof DemoEvent) {
////        }
//        log.info("handleEvent监听到的事件：{}", event);
//    }
//    @EventListener
//    public void handleCustomEvent(OrderEvent orderEvent) {
//        //监听 CustomEvent事件
//        log.info("监听到CustomEvent事件，消息为：{}, 发布时间：{}", orderEvent.getMsg(), orderEvent.getTimestamp());
//    }
//    /**
//     * 监听 code为oKong的事件
//     */
//    @Async
//    @EventListener(condition="#orderEvent.msg == 'xmr'")
//    public void handleCustomEventByCondition(OrderEvent orderEvent) {
//        //监听 CustomEvent事件
//        log.info("监听到msg为'xmr'的orderEvent事件，消息为：{}, 发布时间：{}", orderEvent.getMsg(), orderEvent.getTimestamp());
//    }
//}
