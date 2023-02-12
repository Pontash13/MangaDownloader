package org.example.Service;


//biblioteca para entrar no site do Union
import okhttp3.Response;

import org.example.Config.Consts;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



/**
 * Classe que irá ler todas as infos dos Mangas
 *
 * TODO: Tentar fazer com que ela herde de um serviço..
 * TODO: paginar resposta...
 */
public class UnionManga
{
    private final String site = Consts.SITE_UNION;

    //procura o manga na página
    @NotNull
    public Map<String, String> findManga(String nomeManga) throws IOException
    {
        Map<String, String> dictManga = new HashMap<String, String>();

        String link = site + "busca/" + nomeManga + "/1";//para encontrar no primeiro site
        Response response = Network.Request(link);

        String responseString = Objects.requireNonNull(response.body()).string(); //passando o site como string
        Document soup = Jsoup.parse(responseString, "", org.jsoup.parser.Parser.htmlParser()); //"parseando" site
        Elements containers = soup.select("div.col-md-3.col-xs-6.text-center.bloco-manga");
        for(Element container : containers)
        {
            Elements links = container.select("a");

            dictManga.put(links.get(1).text(), (links.get(0).attr("href")));
        }

        return dictManga;
    }

    //procura os capitulos
    @NotNull
    public Map<String, String> findChapters(String link) throws IOException
    {
        Map<String, String> listChapters = new HashMap<String, String>();

        Response response = Network.Request(link);
        String responseString = Objects.requireNonNull(response.body()).string();

        Document soup = Jsoup.parse(responseString, "", org.jsoup.parser.Parser.htmlParser());
        Elements containers = soup.select("div.row.capitulos");
        for (Element container : containers) {
            Elements links = container.select("a");
            listChapters.put(links.get(0).text(), links.get(0).attr("href"));
        }

        //todo inverter esse array para deixar mais simples
        return Consts.reverseMap(listChapters);
    }

    public Map<String, String> findPages(String link) throws IOException
    {
        Map<String, String> listPages = new HashMap<String, String>();

        Response response = Network.Request(link);
        String responseString = Objects.requireNonNull(response.body()).string();

        Document soup = Jsoup.parse(responseString, "", org.jsoup.parser.Parser.htmlParser());
        Elements containers = soup.select("img.img-manga");

        for (Element container : containers) {
            Elements links = container.select("a");
            listPages.put(container.attr("pag"), container.attr("src"));
        }

        return listPages;
    }

}
