package org.example.Service;

import lombok.SneakyThrows;
import org.example.Config.Consts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Classe responsavel por todos os métodos....
 */
public class NetWorkAPIService {

    @SneakyThrows
    static public String postFormToUrl(String url, String form, String method){

        // Cria uma conexão HTTP
        String params = "";

        if(method == "GET")
            params = form;

        HttpURLConnection connection = (HttpURLConnection) new URL(url + params).openConnection();

        //Tem locais utilizando este método em outros locais...
        connection.setRequestMethod(method);
        connection.setDoOutput(true);


        String data = form;
        byte[] dataBytes = data.getBytes("UTF-8");


        connection.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("x-requested-with", "XMLHttpRequest");


        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(dataBytes);
        }

        // Lê a response da requisição
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
