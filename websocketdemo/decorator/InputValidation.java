package com.example.websocketdemo.decorator;

import com.example.websocketdemo.model.ChatMessage;

import java.util.HashSet;
import java.util.Set;

public class InputValidation implements MessageProcessor {
    private final Set<String> badWords;

    public InputValidation() {
        this.badWords = new HashSet<>();
        badWords.add("bad");
        badWords.add("shot");
        badWords.add("gun");
    }

    @Override
    public void preProcess(ChatMessage message) {

    }

    @Override
    public ChatMessage processMessage(ChatMessage message) {
        if (containsBadWords(message.getContent())) {
            System.out.println("Bad word detected");
            return null;
        }
        return message;
    }

    @Override
    public void postProcess(ChatMessage processed) {

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
