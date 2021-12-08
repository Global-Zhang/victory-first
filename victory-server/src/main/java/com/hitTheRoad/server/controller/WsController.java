package com.hitTheRoad.server.controller;

import com.hitTheRoad.server.pojo.Admin;
import com.hitTheRoad.server.pojo.ChatMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMessage chatMsg){
        Admin admin = (Admin) authentication.getPrincipal();
        chatMsg.setFormNickName(admin.getName());
        chatMsg.setFrom(admin.getUsername());
        chatMsg.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);
    }
}
