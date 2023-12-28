package com.example.websocketdemo.config;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

public interface OriginalWebSocketConfigurer {
    void registerStompEndpoints(StompEndpointRegistry registry);
    void configureMessageBroker(MessageBrokerRegistry registry);
}