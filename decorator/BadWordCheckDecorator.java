package com.example.websocketdemo.decorator;

import com.example.websocketdemo.model.ChatMessage;

import java.util.HashSet;
import java.util.Set;

public class BadWordCheckDecorator implements MessageDecorator {

    private final MessageDecorator messageDecorator;
    private final Set<String> badWords;

    public BadWordCheckDecorator(MessageDecorator messageDecorator) {
        this.messageDecorator = messageDecorator;

        // Додамо сюди слова, які ми хочемо перевіряти
        this.badWords = new HashSet<>();
        badWords.add("bad");
        badWords.add("shot");
        badWords.add("gun");
    }

    @Override
    public ChatMessage processMessage(ChatMessage chatMessage) {
        if (containsBadWords(chatMessage.getContent())) {
            System.out.println("Помилка: Не можна використовувати погані слова.");
            return null; // Повертаємо null, щоб показати, що повідомлення не було оброблено
        } else {
            return messageDecorator.processMessage(chatMessage);
        }
    }

    private boolean containsBadWords(String str) {
        for (String badWord : badWords) {
            if (str != null && str.toLowerCase().contains(badWord)) {
                return true;
            }
        }
        return false;
    }
}
