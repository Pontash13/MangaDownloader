package org.example.Service;


import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Network
{
    static private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(999, TimeUnit.SECONDS)
            .writeTimeout(999, TimeUnit.SECONDS)
            .readTimeout(999, TimeUnit.SECONDS).build();


    @NotNull
    @SneakyThrows
    static public Response Request(String site) throws IOException
    {
        Request request = new Request.Builder()
                .url(site)
                .build();
        return client.newCall(request).execute();
    }


}
