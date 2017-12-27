package com.soft.config;

import com.soft.controllers.HandshakeInterceptor;
import com.soft.controllers.ChatAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        /**
         * 支持websocket 的 connection
         */
        registry.addHandler(new ChatAnnotation(),"/websocket").addInterceptors(new HandshakeInterceptor());

        /**
         * 不支持websocket的connenction,采用sockjs
         */
        registry.addHandler(new ChatAnnotation(),"/sockjs/websocket").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }

    @Bean
    public WebSocketHandler ChatAnnotation() {
        //return new InfoSocketEndPoint();
        return new ChatAnnotation();
    }

}
