package com.example.websocketdemo.decorator;

import com.example.websocketdemo.model.ChatMessage;

public interface MessageProcessor {
    default ChatMessage process(ChatMessage message) {
        preProcess(message);
        ChatMessage processed = processMessage(message);
        postProcess(processed);
        return processed;
    }

    void preProcess(ChatMessage message);
    ChatMessage processMessage(ChatMessage message);
    void postProcess(ChatMessage processed);
}
