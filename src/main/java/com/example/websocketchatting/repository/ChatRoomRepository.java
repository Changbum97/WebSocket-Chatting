package com.example.websocketchatting.repository;

import com.example.websocketchatting.domain.entity.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRooms() {
        // 채팅방 생성 순서 최신순으로 반환
        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        return chatRoom;
    }
}
