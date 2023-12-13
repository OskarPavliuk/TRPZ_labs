package com.example.websocketdemo.mediator;

import com.example.websocketdemo.decorator.BadWordCheckDecorator;
import com.example.websocketdemo.decorator.ConsoleMessageDecorator;
import com.example.websocketdemo.decorator.MessageDecorator;
import com.example.websocketdemo.model.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class DecoratorMediator {

    private final BadWordCheckDecorator badWordCheckDecorator;
    private final ConsoleMessageDecorator consoleMessageDecorator;

    public DecoratorMediator(MessageDecorator messageDecorator) {
        // Ініціалізуємо декоратори тут (можна використати DI)
        this.badWordCheckDecorator = new BadWordCheckDecorator(messageDecorator);
        this.consoleMessageDecorator = new ConsoleMessageDecorator();
    }

    public ChatMessage processMessage(ChatMessage chatMessage) {
        // Взаємодія з BadWordCheckDecorator
        ChatMessage processedMessage = badWordCheckDecorator.processMessage(chatMessage);

        // Взаємодія з ConsoleMessageDecorator
        return consoleMessageDecorator.processMessage(processedMessage);
    }
}