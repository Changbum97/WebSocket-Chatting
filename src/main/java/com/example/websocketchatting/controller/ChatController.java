package com.example.websocketchatting.controller;

import com.example.websocketchatting.domain.dto.ChatRoom;
import com.example.websocketchatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public String findAllRoom() {
        return chatService.findAllRoom().toString();
    }
}
