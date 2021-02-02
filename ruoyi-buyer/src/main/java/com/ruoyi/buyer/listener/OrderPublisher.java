package com.ruoyi.buyer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ztt
 */
@Component
public class OrderPublisher {
   /**
    * 注入 AppllcationContext用来发布事件
    */
   @Autowired
   ApplicationContext applicationContext;

   /**
    * @param msg
    * 使用 AppllicationContext的 publishEvent方法来发布
    */
   public void publish(String msg){
      applicationContext.publishEvent(new OrderEvent(this, msg));
   }

}
