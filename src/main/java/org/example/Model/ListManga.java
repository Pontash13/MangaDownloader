package org.example.Model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ListManga
{
    @SerializedName("series")
    private List<Manga> listManga;

}
