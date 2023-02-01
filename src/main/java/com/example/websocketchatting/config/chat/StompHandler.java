package com.example.websocketchatting.config.chat;

import com.example.websocketchatting.domain.entity.ChatMessage;
import com.example.websocketchatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        log.info("====================");
        log.info("[STOMP Handler] accessor :{}" , accessor);
        // accessor.getCommand => CONNECT, SUBSCRIBE, SEND, DISCONNECT
        log.info("Command : {}", accessor.getCommand());

        // message 추출
        byte[] bytes = (byte[]) message.getPayload();
        log.info("string: {}", new String(bytes, StandardCharsets.UTF_8));

        return message;
    }
}
