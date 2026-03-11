package aniket762.nyayAssist.scraper;

import aniket762.nyayAssist.model.LawSection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class MotorVehicleSectionScraper {

    public LawSection scrapeSection(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(15000)
                    .get();

            // Set title
            Element titleElement = doc.selectFirst("h1");
            if (titleElement == null)
                return null;
            String title = titleElement.text();

            String sectionNumber = title.replaceAll("[^0-9]", "");

            // Set description
            Element content = doc.selectFirst("article");
            String description = "";
            if (content != null)
                description = content.text();
            else
                description = doc.body().text();

            return new LawSection(
                    "Motor Vehicles Act",
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
