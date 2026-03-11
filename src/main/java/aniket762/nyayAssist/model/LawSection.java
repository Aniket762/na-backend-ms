package aniket762.nyayAssist.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LawSection {

    private String act;
    private String section;
    private String title;
    private String description;

    public LawSection() {}

    public LawSection(String act, String section, String title, String description) {
        this.act = act;
        this.section = section;
        this.title = title;
        this.description = description;
    }
}
