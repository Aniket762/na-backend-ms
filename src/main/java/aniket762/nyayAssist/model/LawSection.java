package aniket762.nyayAssist.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LawSection {

    private String act;
    private String chapter;
    private String section;
    private String title;
    private String content;

    public LawSection() {}

    public LawSection(String act, String chapter, String section, String title, String content) {
        this.act = act;
        this.chapter = chapter;
        this.section = section;
        this.title = title;
        this.content = content;
    }
}
