package com.forum.controller;

import com.forum.config.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

/**
 * WebSocketController
 */
@RestController
public class WebSocketController {

    //ResponseEntity<String> 返回类型表示这个方法会返回一个带有消息体和 HTTP 状态码的 ResponseEntity 对象。
    //在这种情况下，会返回一个带有 “请求成功” 消息和状态码 200 OK 的 ResponseEntity 对象。
    @GetMapping("websocket/index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    //ModelAndView 返回类型表示这个方法会返回一个包含视图名称的 ModelAndView 对象。
    //在这种情况下，会返回一个名为 “websocket” 的视图。
    @GetMapping("websocket/page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    //WebSocketServer.sendInfo(message, toUserId) 调用了 WebSocketServer 类的 sendInfo 方法，向指定用户发送消息。
    //这可能是一个用于将消息推送到 WebSocket 客户端的方法。
    @RequestMapping("websocket/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message, toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}