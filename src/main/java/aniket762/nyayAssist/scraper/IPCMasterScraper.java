package aniket762.nyayAssist.scraper;

import aniket762.nyayAssist.model.LawSection;
import aniket762.nyayAssist.util.JsonBatchWriter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IPCMasterScraper {

    private final IPCIndexCrawler crawler;
    private final IPCSectionScraper scraper;

    private static final int BATCH_SIZE = 25;

    public IPCMasterScraper(
            IPCIndexCrawler crawler,
            IPCSectionScraper scraper
    ) {
        this.crawler = crawler;
        this.scraper = scraper;
    }

    public List<LawSection> scrapeAllIPC() {

        List<String> urls = crawler.generateSectionUrls();

        List<LawSection> batch = new ArrayList<>();

        int total = 0;

        for (String url : urls) {

            LawSection law = scraper.scrapeSection(url);

            if (law != null) {

                batch.add(law);
                total++;
            }

            if (batch.size() == BATCH_SIZE) {

                JsonBatchWriter.appendBatch(batch, "ipc_dataset.json");

                batch.clear();

                System.out.println("Batch stored. Total: " + total);
            }

            try {
                Thread.sleep(200);
            } catch (Exception ignored) {}
        }

        if (!batch.isEmpty()) {

            JsonBatchWriter.appendBatch(batch, "ipc_dataset.json");
        }

        System.out.println("Scraping completed. Total: " + total);
        return batch;
    }
}



