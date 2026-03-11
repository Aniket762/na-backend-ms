package aniket762.nyayAssist.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import aniket762.nyayAssist.model.LawSection;

import java.io.File;
import java.util.List;

public class JsonWriterUtil {

    public static void writeToJson(List<LawSection> laws, String fileName) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), laws);

            System.out.println("JSON written to: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
