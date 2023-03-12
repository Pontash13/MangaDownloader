package org.example.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.example.Config.Consts;
import org.example.Model.Chapter;
import org.example.Model.ListChapter;
import org.example.Model.ListManga;
import org.example.Model.Manga;
import org.example.Service.NetWorkAPIService;
import org.example.Service.NetworkScrapService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.util.List;

//Classe que ira manipular os mangas
public class MangaController
{
    private Gson gson = new Gson();
    public ListManga serchManga(String mangaName)
    {
        String response = NetWorkAPIService.postFormToUrl(Consts.GET_SEARCH(),
                "search=" + mangaName, "POST");

        JsonObject json = new JsonParser().parse(response).getAsJsonObject();
        JsonElement seriesElement = json.get("series");
        if (seriesElement != null) {
           if (seriesElement.isJsonPrimitive() && !seriesElement.getAsBoolean())
           {
               return null;
           }
        }
        gson = new Gson();
        ListManga list = gson.fromJson(response, ListManga.class);

        for (Manga manga : list.getListManga())
        {
            String description = catchInfosManga(manga.getIdSerie());
            manga.setDescription(description);
        }
        return list;
    }

    @SneakyThrows
    public List<Chapter> searchChapters(Integer idSerie)
    {
        ListChapter list = new ListChapter();

        Integer count = 0;

        while (true) {
            count++;

            String response = NetWorkAPIService.postFormToUrl(Consts.GET_CHAPTERS(),
                    String.format("?page=%d&id_serie=%d", count, idSerie), "GET");

            JsonObject json = new JsonParser().parse(response).getAsJsonObject();
            JsonElement seriesElement = json.get("chapters");
            if (seriesElement != null) {
                if (seriesElement.isJsonPrimitive() && !seriesElement.getAsBoolean())
                {
                    break;
                }
            }

            gson = new Gson();
            ListChapter chapter = gson.fromJson(response, ListChapter.class);
            list.getListChapters().addAll(chapter.getListChapters());
        }
        return list.getChapters();
    }

    @SneakyThrows
    private String catchInfosManga(Integer idSerie){
        String url = Consts.SITE_MANGA_LIVRE + "/manga/null/" + idSerie;
        String response = NetworkScrapService.requestScrap(url).body().string();

        Document doc = Jsoup.parse(response);
        Element seriesDesc = doc.selectFirst("#series-data > div.series-info.touchcarousel > span.series-desc > span");

        return seriesDesc.text();
    }


    public void downloadImages(String link)
    {

    }


}
