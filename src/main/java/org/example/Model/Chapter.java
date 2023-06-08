package org.example.Model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.example.Model.ChapterModels.Release;

import java.util.Date;
import java.util.Map;

@Data
public class Chapter
{
    @SerializedName("id_serie")
    private Integer id_serie;
    @SerializedName("id_chapter")
    private Integer id_chapter;

    @SerializedName("name")
    private String name;
    @SerializedName("chapter_name")
    private String chapter_name;

    @SerializedName("number")
    private String number;

    @SerializedName("date")
    private String date;

    @SerializedName("date_created")
    private Date date_created;

    @SerializedName("releases")
    private Map<String, Release> releases;

    private String release_first_id;
}



