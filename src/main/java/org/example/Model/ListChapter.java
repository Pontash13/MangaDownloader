package org.example.Model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListChapter {
    @SerializedName("chapters")
    private List<Chapter> listChapters;

    public ListChapter() {
        this.listChapters = new ArrayList<>();
    }

    public List<Chapter> getChapters() {
        return listChapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.listChapters = chapters;
    }

}
