package org.example.Config;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

//CLASSE RES
public class Consts
{
    //Site que iremos utilizar para criar fazer o acesso:
    static public String SITE_UNION = "https://unionleitor.top/";
    static public String SITE_MANGA_LIVRE;


    //inverte um map
    public static <K, V> Map<K, V> reverseMap(@NotNull Map<K, V> map) {
        Map<K, V> reversedMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            reversedMap.put(entry.getKey(), entry.getValue());
        }
        return reversedMap;
    }

    //Função para facilitar pegar um index de um map...
    public static <K, V> Object getItemByIndexMap(@NotNull Map<K, V> map, Integer index)
    {
        Object key = map.keySet().toArray()[index];
        Object value = map.get(key);
        return value;
    }

}
