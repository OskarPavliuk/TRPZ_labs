package com.example.websocketdemo.decorator;


import com.example.websocketdemo.model.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ConsoleMessageValidation implements MessageProcessor {
    @Override
    public void preProcess(ChatMessage message) {

    }

    @Override
    public ChatMessage processMessage(ChatMessage message) {
        System.out.println("Received message: " + message.getContent());
        return message;
    }

    @Override
    public void postProcess(ChatMessage processed) {

    }
}
