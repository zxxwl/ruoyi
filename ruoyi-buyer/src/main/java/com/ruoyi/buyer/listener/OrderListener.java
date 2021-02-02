package com.ruoyi.buyer.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * 实现 ApplicationListener接口, 并指定监听的事件类型
 * @author ztt
 */
@Component
public class OrderListener implements ApplicationListener<OrderEvent> {

   /**
    * @param event
    * 使用 onApplicationEvent方法对消息进行接受处理
    */
   @Override
   public void onApplicationEvent(OrderEvent event) {
      Object msg = event.getMsg();
      System.out.println("我接受到了用户下单发布的消息:"
            + msg);
   }

}
