package com.example.websocketdemo.decorator;


import com.example.websocketdemo.model.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ConsoleMessageDecorator implements MessageDecorator {

    @Override
    public ChatMessage processMessage(ChatMessage chatMessage) {
        if (chatMessage != null && chatMessage.getContent() != null) {
            System.out.println("Received message: " + chatMessage.getContent());
        } else {
            System.out.println("Received message is illegal or null");
        }
        return chatMessage;
    }
}