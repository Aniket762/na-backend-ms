package aniket762.nyayAssist.util;

import aniket762.nyayAssist.model.LawSection;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.util.List;

public class JsonBatchWriter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void appendBatch(List<LawSection> batch, String file) {

        try (FileWriter writer = new FileWriter(file, true)) {

            for (LawSection law : batch) {

                String json = mapper.writeValueAsString(law);

                writer.write(json);
                writer.write(",\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
