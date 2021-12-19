package com.whosaidmeow.wsmjms.sender;

import com.whosaidmeow.wsmjms.config.JmsConfig;
import com.whosaidmeow.wsmjms.domain.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("Sending message.....");

        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello world!")
                .build();
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, helloWorldMessage);
        System.out.println("Message sent!");
    }
}
