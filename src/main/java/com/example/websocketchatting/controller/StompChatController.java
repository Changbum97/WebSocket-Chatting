package com.example.websocketchatting.controller;

import com.example.websocketchatting.domain.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class StompChatController {

    // 특정 Broker로 메세지 전달
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/enter")
    public void enter(ChatMessage chatMessage) {
        chatMessage.setMessage(chatMessage.getWriter() + "님이 채팅방에 입장했습니다.");
        template.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping("/chat/message")
    public void send(ChatMessage chatMessage) {
        template.convertAndSend("/sub/chat/room" + chatMessage.getRoomId(), chatMessage);
    }
}
