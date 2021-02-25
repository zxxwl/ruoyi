package com.ruoyi.buyer.controller;

import com.ruoyi.buyer.socket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ruoyi
 * @description: websocket调用类
 * @author: ztt
 * @create: 2021-02-24 17:40
 */
@RestController
@RequestMapping("/test/order")
public class WebSocketController {
    @Autowired
    private WebSocket webSocket;

    @GetMapping("/a")
    public String a(){
        String text="李四发送下单完成!";
        webSocket.sendOneMessage("admin",text);
        return text;
    }
}
