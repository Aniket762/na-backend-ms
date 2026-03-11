package aniket762.nyayAssist.scraper;

import aniket762.nyayAssist.model.LawSection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class IPCSectionScraper {

    public LawSection scrapeSection(String url) {

        try {

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(15000)
                    .get();

            Element titleElement = doc.selectFirst("h1");

            if (titleElement == null)
                return null;

            String title = titleElement.text();

            String sectionNumber =
                    title.replaceAll("[^0-9]", "");

            String description = "";

            Element content =
                    doc.selectFirst("article");

            if (content != null) {
                description = content.text();
            } else {
                description = doc.body().text();
            }

            return new LawSection(
                    "IPC",
                    sectionNumber,
                    title,
                    description
            );

        } catch (Exception e) {

            System.out.println("Failed: " + url);
            return null;
        }
    }
}

