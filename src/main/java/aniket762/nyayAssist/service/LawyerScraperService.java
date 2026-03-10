package aniket762.nyayAssist.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class LawyerScraperService {

    @Tool(description = "Search lawyers by city and specialty")
    public String searchLawyers(
            @ToolParam(description = "City to search") String location,
            @ToolParam(description = "Law specialization") String specialty
    ) {
        return "Searching for " + specialty + " lawyers in " + location;
    }
}