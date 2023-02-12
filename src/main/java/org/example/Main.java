package org.example;


import lombok.SneakyThrows;

import org.example.Config.Consts;
import org.example.Service.UnionManga;

import java.util.Map;


public class Main
{

    @SneakyThrows
    public static void main(String[] args)
    {
        UnionManga u = new UnionManga();

        Map<String, String> chain = u.findManga("chainsaw man");
        String a = Consts.getItemByIndexMap(chain, 1).toString();
        System.out.println(a);
        System.out.println("fim");
    }
}
