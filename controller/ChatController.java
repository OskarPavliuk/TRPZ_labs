package com.example.websocketdemo.controller;

import com.example.websocketdemo.decorator.MessageDecorator;
import com.example.websocketdemo.decorator.BadWordCheckDecorator;
import com.example.websocketdemo.decorator.ConsoleMessageDecorator;
import com.example.websocketdemo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    private final MessageDecorator messageDecorator;

    public ChatController() {
        // Ініціалізуємо декоратори тут (можна використати DI)
        this.messageDecorator = new BadWordCheckDecorator(new ConsoleMessageDecorator());
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        ChatMessage processedMessage = messageDecorator.processMessage(chatMessage);
        return processedMessage != null ? processedMessage : new ChatMessage(); // Повертаємо пусте повідомлення у випадку помилки
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {

        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }
}
