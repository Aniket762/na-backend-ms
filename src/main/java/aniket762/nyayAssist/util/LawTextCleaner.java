package aniket762.nyayAssist.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class LawTextCleaner {

    private static final List<String> NOISE = Arrays.asList(
            "No Javascript",
            "Please Enable Javascript",
            "Home",
            "Index",
            "Next",
            "Back",
            "Messages",
            "Top",
            "Users Online",
            "TimeTaken",
            "A Lawyers Reference",
            "©",
            "All Sections Lists"
    );

    public static String clean(String text) {
        if (text == null) return "";
        String cleaned = text;
        for (String n : NOISE) {
            cleaned = cleaned.replace(n, "");
        }

        // regex is AI generated
        cleaned = cleaned.replaceAll("(?<=[a-zA-Z])\\d+(?=\\s|\\.|,|;|\\))", "");
        cleaned = cleaned.replace("\n\n", " ");
        cleaned = cleaned.replace("\n", " ");
        cleaned = cleaned.replaceAll("\\s+", " ");
        cleaned = cleaned.replaceAll("\\s*\\((\\d+|[a-z])\\)\\s*", " ($1) ");
        cleaned = cleaned.trim();

        return cleaned;
    }

    public static String extractChapter(String content) {
        if (content == null) return "Unknown";

        java.util.regex.Matcher matcher = Pattern
                .compile("(?i)chapter\\s+([IVXLCDM]+|\\d+|[A-Z]+)")
                .matcher(content);
        if (matcher.find()) {
            return matcher.group(0).trim();
        }
        return "Unknown";
    }
}