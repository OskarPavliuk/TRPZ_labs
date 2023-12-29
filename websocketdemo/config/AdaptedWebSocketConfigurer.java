package com.example.websocketdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Component
public class AdaptedWebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

    private final WebSocketConfig webSocketConfig;

    @Autowired
    public AdaptedWebSocketConfigurer(WebSocketConfig webSocketConfig) {
        this.webSocketConfig = webSocketConfig;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        webSocketConfig.registerStompEndpoints(registry);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        webSocketConfig.configureMessageBroker(registry);
    }
}