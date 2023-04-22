package org.example.Model.ChapterModels;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ListPages {

    @SerializedName("images")
    public List<Page> pages;
}
