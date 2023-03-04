package org.example.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
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
    private String folderName  = "MangaDownloader";


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
        Path folderPath = Paths.get(folderCache, folderName);
        File folder = folderPath.toFile();

        // Verifica se a pasta MangaDownloader já existe
        if (!folder.exists()) {
            // Tenta criar a pasta MangaDownloader
            if (!folder.mkdir()) {
                // Se a pasta não puder ser criada, retorna null
                return null;
            }
        }

        // Cria o diretório temporário dentro da pasta MangaDownloader
        Path path = Paths.get(folderCache, folderName, returnNameTemp());
        File tempFolder = path.toFile();
        if (tempFolder.mkdir() || tempFolder.exists()) {
            return String.valueOf(path);
        }
        
        return null;
    }

    //Eu posso passar uma string e rodar todos as pastas que eu queira deletar
    public void deleteFolder(ArrayList<String> folders) throws IOException
    {
        for (String f : folders) 
        {
            File folder = new File(f);
            if (folder.exists() && folder.isDirectory()) 
            {
                File[] files = folder.listFiles();
                if (files != null) 
                {
                    for (File file : files) 
                    {
                        file.delete();   
                    }  
                }
                folder.delete();
            }
        }
    }

    //lista todos a pastas no diretório
    public ArrayList<String> allFolderInPath(String path)
    {
        
        //se eu não passar, vou apagar tudo que está na pasta
        if(path == null){
            path = String.valueOf(Paths.get(folderCache, folderName));
        }
        ArrayList<String> arrayFolder = new ArrayList<String>();
        File[] files = new File(path).listFiles();

        for (File file  : Objects.requireNonNull(files)) 
        {
            arrayFolder.add(String.valueOf(file));
        }
        return arrayFolder;
    }
    
    //download da imagem
    public void downloadImagem(String url, String name, String folderPath) 
    {
        try {
            URL urlImagem  = new URL(url);
            InputStream in = urlImagem.openStream();
            String file_extension = url.substring(url.lastIndexOf(".") + 1);
            Path folder    = Paths.get(folderPath);
            Files.copy(in, folder.resolve(name + "." + file_extension));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

