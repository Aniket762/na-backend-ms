package aniket762.nyayAssist.scraper;

import aniket762.nyayAssist.model.LawSection;
import aniket762.nyayAssist.util.JsonBatchWriter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotorVehicleMasterScraper {

    private final MotorVehicleIndexCrawler crawler;
    private final MotorVehicleSectionScraper scraper;

    private static final int BATCH_SIZE = 25;

    public MotorVehicleMasterScraper(MotorVehicleIndexCrawler crawler, MotorVehicleSectionScraper scraper)
    {
        this.crawler = crawler;
        this.scraper = scraper;
    }

    public void scrapeAll() {

        List<String> urls = crawler.generateSectionUrls();
        List<LawSection> batch = new ArrayList<>();

        int total = 0;
        for (String url : urls) {
            LawSection law = scraper.scrapeSection(url);
            if (law != null && law.getDescription().length() > 100) {
                batch.add(law);
                total++;
            }

            // Adding batch processing for optimization
            if (batch.size() == BATCH_SIZE) {
                JsonBatchWriter.appendBatch(batch, "motor_vehicle_dataset.json");
                batch.clear();
                System.out.println("Batch stored. Total: " + total);
            }

            try {
                Thread.sleep(200);
            } catch (Exception ex) {
                System.out.println("Failed: " + ex);
            }
        }

        // appending the data less than batch_size
        if (!batch.isEmpty()) {
            JsonBatchWriter.appendBatch(batch, "motor_vehicle_dataset.json"
            );
        }

        System.out.println("Motor vehicle scraping complete: " + total);
    }
}
