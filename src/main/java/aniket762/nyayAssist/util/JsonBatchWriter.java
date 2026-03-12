package aniket762.nyayAssist.util;

import aniket762.nyayAssist.model.LawSection;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.List;

public class JsonBatchWriter {

    private static final String OUTPUT_DIR = "src/main/resources/data/";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

    public static void appendBatch(List<LawSection> batch, String file) {
        try {
            File f = new File(OUTPUT_DIR + file);
            f.getParentFile().mkdirs();

            if (!f.exists() || f.length() == 0) {
                try (FileWriter fw = new FileWriter(f, false)) {
                    fw.write("[\n");
                }
            } else {
                try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
                    long length = raf.length();
                    for (long pos = length - 1; pos >= 0; pos--) {
                        raf.seek(pos);
                        char c = (char) raf.readByte();
                        if (c == ']') {
                            raf.setLength(pos);
                            break;
                        }
                    }
                }
                try (FileWriter fw = new FileWriter(f, true)) {
                    fw.write(",\n");
                }
            }

            try (FileWriter fw = new FileWriter(f, true)) {
                for (int i = 0; i < batch.size(); i++) {
                    fw.write(writer.writeValueAsString(batch.get(i)));
                    if (i < batch.size() - 1) {
                        fw.write(",\n");
                    }
                }
                fw.write("\n]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}