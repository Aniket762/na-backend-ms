package aniket762.nyayAssist.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IPCIndexCrawler {

    public List<String> generateSectionUrls() {
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 511; i++) {
            String url = "https://devgan.in/ipc/section/" + i + "/";
            urls.add(url);
        }
        System.out.println("Generated URLs: " + urls.size());
        return urls;
    }
}

