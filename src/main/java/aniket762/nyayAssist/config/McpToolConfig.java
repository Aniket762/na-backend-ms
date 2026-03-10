package aniket762.nyayAssist.config;

import aniket762.nyayAssist.service.LawyerScraperService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolConfig {

    @Bean
    public ToolCallbackProvider lawyerTools(LawyerScraperService lawyerScraperService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(lawyerScraperService)
                .build();
    }
}