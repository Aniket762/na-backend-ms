package aniket762.nyayAssist.service;

import aniket762.nyayAssist.model.LawSection;
import aniket762.nyayAssist.util.JsonBatchWriter;
import aniket762.nyayAssist.util.LawTextCleaner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GithubLawLoaderService {

    private static final String BASE_RAW_URL = "https://raw.githubusercontent.com/civictech-India/Indian-Law-Penal-Code-Json/main/";
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();

    public enum LawSource {
        IPC("ipc.json", "Indian Penal Code"),
        MVA("MVA.json", "Motor Vehicles Act"),
        CPC("cpc.json", "Civil Procedure Code"),
        HMA("hma.json", "Hindu Marriage Act"),
        IEA("iea.json", "Indian Evidence Act"),
        NIA("nia.json", "Negotiable Instruments Act");

        public final String fileName;
        public final String actName;

        LawSource(String fileName, String actName) {
            this.fileName = fileName;
            this.actName = actName;
        }
    }

    public void loadLaw(LawSource source, String outputFile) {
        String url = BASE_RAW_URL + source.fileName;
        System.out.println("Fetching: " + url);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Failed to fetch: " + url + " | Status: " + response.statusCode());
                return;
            }

            JsonNode root = mapper.readTree(response.body());
            List<LawSection> batch = new ArrayList<>();
            int total = 0;
            int skipped = 0;

            for (JsonNode node : root) {
                String section = getField(node,
                        "section", "sectionNumber", "section_number",
                        String.valueOf(total + skipped + 1));

                String title = getField(node,
                        "section_title", "title", "sectionTitle",
                        "Untitled");

                String rawContent = getField(node,
                        "section_desc", "description", "content", "text",
                        "");

                if (rawContent.length() < 50) {
                    System.out.println("Skipped section " + section + " - content too short");
                    skipped++;
                    continue;
                }

                String cleanContent = LawTextCleaner.clean(rawContent);

                String chapter = getField(node,
                        "chapter_title", "chapter", "chapterTitle",
                        "");
                if (chapter.isBlank() || chapter.equals("Unknown")) {
                    chapter = LawTextCleaner.extractChapter(cleanContent);
                }

                batch.add(new LawSection(source.actName, chapter, section, title, cleanContent));
                total++;

                if (batch.size() >= 25) {
                    JsonBatchWriter.appendBatch(batch, outputFile);
                    batch.clear();
                    System.out.println("Batch stored. Total so far: " + total);
                }
            }

            if (!batch.isEmpty()) {
                JsonBatchWriter.appendBatch(batch, outputFile);
            }

            System.out.println("Completed " + source.actName + " | Saved: " + total + " | Skipped: " + skipped);

        } catch (Exception e) {
            System.out.println("Error loading " + source.actName + " | Reason: " + e.getMessage());
        }
    }

    private String getField(JsonNode node, String... keys) {
        for (int i = 0; i < keys.length - 1; i++) {
            JsonNode field = node.get(keys[i]);
            if (field != null && !field.isNull() && !field.asText().isBlank()) {
                return field.asText().trim();
            }
        }
        return keys[keys.length - 1];
    }
}