package org.example.Config;


//todo, retirar essas contantes assim, pois no futuro poderei utilizar outros sites
public class Consts {
    static public String SITE_MANGA_LIVRE = "https://mangalivre.net";

    static public String ENDPOINT_SEARCH = "/lib/search/series.json";

    static public String ENDPOINT_CHAPTERS = "/series/chapters_list.json";

    static public String ENDPOINT_PAGE_MANGA = "/manga/null";

    static public String ENDPOINT_PAGES = "/leitor/pages/";

    static public String GET_SEARCH() { return SITE_MANGA_LIVRE + ENDPOINT_SEARCH; }
    static public String GET_PAGE_MANGA() { return SITE_MANGA_LIVRE + ENDPOINT_PAGE_MANGA; }
    static public String GET_CHAPTERS() { return SITE_MANGA_LIVRE + ENDPOINT_CHAPTERS; }
    static public String GET_PAGES(Integer idRelease)
    { return SITE_MANGA_LIVRE + ENDPOINT_PAGES + idRelease.toString() +".json?key=."; }

}
