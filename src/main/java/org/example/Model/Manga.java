package org.example.Model;

import lombok.Data;

import java.util.ArrayList;

/**
 *  A classe de mangas, serve para que possamos ter uma estilização das infos de uma maneira bem mais fácil
 *
 */
@Data
public class Manga
{
    private String name;
    private String Author;
    private String date;
    private String link;
    private ArrayList<String> chapters;
}
