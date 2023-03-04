package org.example;



import org.example.Config.Consts;
import org.example.Controller.SystemController;
import org.example.Service.UnionManga;

import java.io.IOException;
import java.util.Map;


public class Main
{


    public static void main(String[] args) throws IOException
    {
        UnionManga u = new UnionManga();
        SystemController s = new SystemController();

        Map<String, String> chain = u.findManga("chainsaw man");
        String a = Consts.getItemByIndexMap(chain, 1).toString();
        Map<String, String> chapter = u.findChapters(a);
        String b  = Consts.getItemByIndexMap(chapter, 1).toString();
        Map<String, String> pag = u.findPages(b);
 
        //limpar tudo por garantia
        s.deleteFolder(s.allFolderInPath(null));         
        String pasta = s.createFolder();
        System.out.println(pasta);
        
        for (Map.Entry<String, String> i : pag.entrySet()) {
            s.downloadImagem(i.getValue(), i.getKey(), pasta);
        }
        

        
       
        
    }
}
