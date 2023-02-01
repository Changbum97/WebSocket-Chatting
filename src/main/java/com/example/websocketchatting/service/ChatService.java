package com.example.websocketchatting.service;

import com.example.websocketchatting.domain.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    // 특정 Broker로 메세지 전달
    private final SimpMessagingTemplate template;

    public void sendChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            chatMessage.setMessage(chatMessage.getWriter() + "님이 방에 입장했습니다.");
        } else if(chatMessage.getType().equals(ChatMessage.MessageType.QUIT)) {
            chatMessage.setMessage(chatMessage.getWriter() + "님이 방에서 나갔습니다.");
        }
        //template.convertAndSend("/sub/chat/room" + chatMessage.getRoomId(), chatMessage);

        template.convertAndSend("/sub/chat/room" + chatMessage.getRoomId(), chatMessage);
    }
}
