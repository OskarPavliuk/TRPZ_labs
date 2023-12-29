package com.example.websocketdemo.controller;

import com.example.websocketdemo.decorator.InputValidation;
import com.example.websocketdemo.mediator.MediatorImpl;
import com.example.websocketdemo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    private final MediatorImpl mediatorImpl;

    public ChatController() {
        this.mediatorImpl = MediatorImpl.getInstance(new InputValidation());
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        ChatMessage processedMessage = mediatorImpl.processMessage(chatMessage);
        // Перевіряємо, чи повідомлення не містить неприпустимих слів
        if (processedMessage == null) {
            // Створюємо повідомлення про помилку
            ChatMessage errorMessage = new ChatMessage();
            errorMessage.setSender("System");
            errorMessage.setType(ChatMessage.MessageType.CHAT);
            errorMessage.setContent("Bad words are prohibited!");
            return errorMessage;
        }

        return processedMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {

        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }
}
