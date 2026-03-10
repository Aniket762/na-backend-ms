package aniket762.nyayAssist.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class LawyerScraperService {

    @Tool(description = "Scrapes the internet to find top-rated lawyers based on a specific location and specialty.")
    public String searchLawyers(
            @ToolParam(description = "The city or region to search in") String location,
            @ToolParam(description = "The legal specialty, e.g., 'Criminal', 'Corporate'") String specialty) {
        return "Searching for " + specialty + " lawyers in " + location + "...";
    }
}