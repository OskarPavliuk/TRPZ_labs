package com.example.websocketdemo.mediator;

import com.example.websocketdemo.decorator.MessageProcessor;
import com.example.websocketdemo.model.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class MediatorImpl {

    private static MediatorImpl instance;
    private final MessageProcessor messageProcessor;

    private MediatorImpl(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public static MediatorImpl getInstance(MessageProcessor messageProcessor) {
        if (instance == null) {
            instance = new MediatorImpl(messageProcessor);
        }
        return instance;
    }

    public ChatMessage processMessage(ChatMessage chatMessage) {
        return messageProcessor.process(chatMessage);
    }
}

