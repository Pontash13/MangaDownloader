package org.example.Service;

import lombok.SneakyThrows;
import org.example.Config.Consts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWorkAPIService {

    @SneakyThrows
    static public String postFormToUrl(String url, String form, String method){

        // Cria uma conexão HTTP
        String params = "";

        if(method == "GET")
            params = form;

        HttpURLConnection conexao = (HttpURLConnection) new URL(url + params).openConnection();

        //Tem locais utilizando este método em outros locais...
        conexao.setRequestMethod(method);
        conexao.setDoOutput(true);


        String data = form;
        byte[] dataBytes = data.getBytes("UTF-8");


        conexao.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        conexao.setRequestProperty("x-requested-with", "XMLHttpRequest");


        try (OutputStream outputStream = conexao.getOutputStream()) {
            outputStream.write(dataBytes);
        }

        // Lê a resposta da requisição
        BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String line;
        StringBuilder resposta = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            resposta.append(line);
        }
        reader.close();

        return resposta.toString();
    }
}
