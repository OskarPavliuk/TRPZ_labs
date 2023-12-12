package com.example.websocketdemo.decorator;

import com.example.websocketdemo.model.ChatMessage;

public interface MessageDecorator {
    ChatMessage processMessage(ChatMessage chatMessage);
}
