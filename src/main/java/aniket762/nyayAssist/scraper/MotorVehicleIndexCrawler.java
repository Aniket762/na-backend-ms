package aniket762.nyayAssist.scraper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotorVehicleIndexCrawler {

    public List<String> generateSectionUrls() {
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 217; i++) {
            String url = "https://devgan.in/motor-vehicles-act/section/" + i + "/";
            urls.add(url);
        }

        System.out.println("Generated Motor Vehicle URLs: " + urls.size());
        return urls;
    }
}
