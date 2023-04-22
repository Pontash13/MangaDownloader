package org.example.Controller;


import lombok.SneakyThrows;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

//Irá formatar o manga em um formato feliz
public class FormatController
{
    @SneakyThrows
    public void createEPUB(String folder, String name, String cap)
    {
        // Cria um novo livro EPUB
        Book book = new Book();

        Author author = new Author(name + "_" +cap);
        // Define os metadados do livro
        book.getMetadata().addTitle(name);
        book.getMetadata().addAuthor(author);

        File[] files =  new File(folder).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                // Carrega a imagem
                BufferedImage image = ImageIO.read(file);

                // Cria um arquivo HTML para exibir a imagem
                String fileName = file.getName().replaceAll("\\..*", "") + ".html";
                File htmlFile = new File(folder + "\\" +fileName);
                BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
                writer.write("<html><body><img src=\"" + file.getName() + "\"></body></html>");
                writer.close();

                // Adiciona o arquivo HTML ao EPUB
                Resource resource = new Resource(htmlFile.toURI().toURL().openStream(), "text/html");
                book.getResources().add(resource);
            }
        }

        // Salva o livro EPUB
        EpubWriter epubWriter = new EpubWriter();
        epubWriter.write(book, new FileOutputStream(SystemController.folderSaveManga + "\\" +
                name + "_" + cap + ".epub" ));
    }
}
