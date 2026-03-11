package aniket762.nyayAssist.controller;

import aniket762.nyayAssist.model.LawSection;
import aniket762.nyayAssist.scraper.IPCMasterScraper;
import aniket762.nyayAssist.util.JsonWriterUtil;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scrape")
public class ScrapeController {

    private final IPCMasterScraper scraper;

    public ScrapeController(IPCMasterScraper scraper) {
        this.scraper = scraper;
    }

    @GetMapping("/ipc-full")
    public String scrapeFullIPC() {

        List<LawSection> laws =
                scraper.scrapeAllIPC();

        JsonWriterUtil.writeToJson(
                laws,
                "ipc_full_dataset.json"
        );

        return "IPC scraped: " + laws.size();
    }
}
