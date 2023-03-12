package org.example.Model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

/**
 *  A classe de mangas, serve para que possamos ter uma estilização das infos de uma maneira bem mais fácil
 *
 */
@Data
public class Manga
{

    @SerializedName("id_serie")
    private Integer idSerie;

    @SerializedName("name")
    private String name;

    @SerializedName("label")
    private String label;

    @SerializedName("score")
    private String score;

    @SerializedName("value")
    private String value;

    @SerializedName("author")
    private String author;

    @SerializedName("artist")
    private String artist;

    @SerializedName("cover")
    private String cover;

    @SerializedName("cover_thumb")
    private String coverThumb;

    @SerializedName("cover_avif")
    private String coverAvif;

    @SerializedName("cover_thumb_avif")
    private String coverThumbAvif;

    @SerializedName("link")
    private String link;

    @SerializedName("is_complete")
    private boolean isComplete;

    private String description;
    private List<Chapter> chapters;
}