package com.example.websocketchatting.controller;

import com.example.websocketchatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomRepository  chatRoomRepository;

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        log.info("All Chat Rooms");
        model.addAttribute("rooms", chatRoomRepository.findAllRooms());
        return "chat-list";
    }

    // 채팅방 개설
    @PostMapping("/room")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr) {
        log.info("Create Chat Room, name : {}", name);
        rttr.addFlashAttribute("newRoom", chatRoomRepository.createChatRoom(name));
        return "redirect:/chat/rooms";
    }

    // 채팅방 조회
    @GetMapping("/room")
    public String getRoom(String roomId, Model model, Principal principal) {
        log.info("Get Chat Room, roomId : {}", roomId);
        model.addAttribute("userName", principal.getName());
        model.addAttribute("room", chatRoomRepository.findRoomById(roomId));
        return "chat-room";
    }
}
