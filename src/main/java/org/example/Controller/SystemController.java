package org.example.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * Todo, ler e armazenar os mangas -- também ter uma local para que eu possa ver as atualizações
 *
 */
public class SystemController
{
    //Pasta padrão de criação:
    private String folderCache = System.getProperty("java.io.tmpdir");

    //retorno de um nome aleatório
    //?? talvez deva ser uma enum...
    private String returnNameTemp()
    {
        Random rand = new Random();
        return rand.ints('a', 'z' + 1)
                .limit(15)
                .mapToObj(i -> (char) i)
                .map(Object::toString)
                .collect(Collectors.joining());

    }

    //Cria uma pasta para colocar os arquivos temporários dentro
    public String createFolder() throws IOException
    {
        Path path = Paths.get(folderCache, "MangaDownloader", returnNameTemp());
        File folder = new File(String.valueOf(path));

        //se a pasta for criada....
        if (folder.mkdir() || folder.exists())
            return String.valueOf(path);

        return null;
    }

    //Eu posso passar uma string e rodar todos as pastas que eu queira deletar
    public void deleteFolder(ArrayList<String> folders) throws IOException
    {
        for (String path : folders) {
            File f = new File(String.valueOf(Paths.get(path)));
            if ((f.exists()) && (f.isDirectory()))
                f.delete();
        }
    }

    public ArrayList<String> allFolderInPath(String path)
    {
        ArrayList<String> arrayFolder = new ArrayList<String>();
        File[] files = new File(path).listFiles();

        for (File file  : Objects.requireNonNull(files)) {
            arrayFolder.add(String.valueOf(file));
        }
        return arrayFolder;
    }

}

