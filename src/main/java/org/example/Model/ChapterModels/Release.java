package org.example.Model.ChapterModels;

import com.sun.javafx.image.IntPixelGetter;
import lombok.Data;

import com.google.gson.annotations.SerializedName;


@Data
public class Release {

    @SerializedName("id_release")
    private Integer id_release;
    @SerializedName("views")
    private Integer views;
    @SerializedName("link")
    private String link;
}
