package org.example.Model.ChapterModels;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Page {
    @SerializedName("legacy")
    public String legacy;
    @SerializedName("avif")
    public String avif;
}
