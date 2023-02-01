package com.example.websocketchatting.controller;

import com.example.websocketchatting.domain.entity.ChatMessage;
import com.example.websocketchatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class StompChatController {

    // 특정 Broker로 메세지 전달
    private final SimpMessagingTemplate template;
    //private final ChatService chatService;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessage chatMessage) {
        chatMessage.setType(ChatMessage.MessageType.ENTER);
        //chatService.sendChatMessage(chatMessage);
        chatMessage.setMessage(chatMessage.getWriter() + "님이 채팅방에 입장했습니다.");
        template.convertAndSend("/sub/chat/room" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping(value = "/chat/message")
    public void send(ChatMessage chatMessage) {
        chatMessage.setType(ChatMessage.MessageType.TALK);
        template.convertAndSend("/sub/chat/room" + chatMessage.getRoomId(), chatMessage);
        //chatService.sendChatMessage(chatMessage);
    }
}
