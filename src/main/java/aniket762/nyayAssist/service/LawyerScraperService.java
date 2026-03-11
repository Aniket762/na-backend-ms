package aniket762.nyayAssist.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class LawyerScraperService {

    @Tool(description = "Search lawyers by city and specialty")
    public String searchLawyers(
            @ToolParam(description = "City to search") String location,
            @ToolParam(description = "Law specialization") String specialty
    ) {
        String content = """
                Lawyer Search Results
                =====================
                Location  : %s
                Specialty : %s
                Timestamp : %s
                
                Results:
                - Lawyer 1: John Doe | %s | %s
                - Lawyer 2: Jane Smith | %s | %s
                - Lawyer 3: Raj Mehta | %s | %s
                """.formatted(
                location, specialty, LocalDateTime.now(),
                specialty, location,
                specialty, location,
                specialty, location
        );

        writeToFile(location, specialty, content);

        return "Results written to file for " + specialty + " lawyers in " + location;
    }

    private void writeToFile(String location, String specialty, String content) {
        try {
            Path dirPath = Paths.get("src/main/resources/output");
            Files.createDirectories(dirPath);

            String fileName = location + "_" + specialty.replace(" ", "_") + ".txt";
            Path filePath = dirPath.resolve(fileName);

            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(content);
            }

            System.out.println("FILE WRITTEN → " + filePath.toAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Failed to write results to file", e);
        }
    }
}
