package aniket762.nyayAssist.config;

import aniket762.nyayAssist.service.LawyerScraperService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder,
                                 LawyerScraperService lawyerScraperService) {
        return builder
                .defaultTools(lawyerScraperService)
                .build();
    }
}