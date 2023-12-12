package com.example.websocketdemo.decorator;


import com.example.websocketdemo.model.ChatMessage;

public class ConsoleMessageDecorator implements MessageDecorator {

    @Override
    public ChatMessage processMessage(ChatMessage chatMessage) {
        System.out.println("Received message: " + chatMessage.getContent());
        return chatMessage;
    }
}
