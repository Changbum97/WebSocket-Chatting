package com.example.websocketchatting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomCreateRequest {
    private Long user1Id;
    private Long user2Id;
}
