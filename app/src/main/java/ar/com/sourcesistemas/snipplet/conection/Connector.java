package ar.com.sourcesistemas.snipplet.conection;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import ar.com.sourcesistemas.snipplet.AdministrarNubeActivity;
import ar.com.sourcesistemas.snipplet.MainActivity;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.UserConfiguration;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

import ar.com.sourcesistemas.snipplet.dto.SendDTO;
import ar.com.sourcesistemas.snipplet.services.ConfigurationService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Connector {
private Context context;


    ConfigurationService configurationService = new ConfigurationService();
    final OkHttpClient client = new OkHttpClient();
    private String[] directorios = null;
    private DatabaseHandler databaseHandler;

    public Connector(Context context){

        databaseHandler = new DatabaseHandler(context,null,null,1);

    }


    public String[] list(final LinearLayout linearLayout,final AdministrarNubeActivity context) throws IOException {
        configurationService = new ConfigurationService();
        String url = configurationService.getUri() + "listarServer";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        UserConfiguration userConfiguration = configurationService.getUserConfiguration();
        userConfiguration = new UserConfiguration();
        userConfiguration.setUsername("martin");
        userConfiguration.setPassword("nada");
        SendDTO send = new SendDTO();
        send.setUsername(userConfiguration.getUsername());
        send.setPassword(userConfiguration.getPassword());


        final ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(JSON, mapper.writeValueAsString(send));

        final Request request = new Request.Builder().url(url).post(body).build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                String responseBody = response.body().string();

                directorios = mapper.readValue(responseBody, String[].class);

                context.setLista(directorios);

                response.close();


            }
        });


        return directorios;
    }





/*
    public void borrarDelServer() throws IOException {
        String url = configurationService.getUri() + "deleteCategory";
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre(categoria);
        SendDTO send = new SendDTO();
        UserConfiguration userConfiguration = configurationService.getUserConfiguration();
        send.setUsername(userConfiguration.getUsername());
        send.setCategoriaDTO(categoriaDTO);


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String writeValueAsString = mapper.writeValueAsString(send);
        System.out.println(writeValueAsString);
        RequestBody body = RequestBody.create(JSON, writeValueAsString);

        Request request = new Request.Builder().url(url).post(body).build();
        okhttp3.Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
    }
    */

    public CategoriaDTO getFromServer(String nombreCategoria) throws IOException {

        String url = configurationService.getUri() + "returnCategory";
        CategoriaDTO recuperarGuardado = new CategoriaDTO();
        recuperarGuardado.setNombre(nombreCategoria);

        SendDTO send = new SendDTO();
        UserConfiguration userConfiguration = configurationService.getUserConfiguration();
        send.setUsername("martin");
        send.setCategoriaDTO(recuperarGuardado);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        final ObjectMapper mapper = new ObjectMapper();
        String writeValueAsString = mapper.writeValueAsString(send);
        System.out.println(writeValueAsString);
        RequestBody body = RequestBody.create(JSON, writeValueAsString);

        Request request = new Request.Builder().url(url).post(body).build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                String responseBody = response.body().string();

                CategoriaDTO categoriaDTO = mapper.readValue(responseBody, CategoriaDTO.class);
                System.out.println("categoria nombre: "+categoriaDTO.getNombre());
                databaseHandler.addCategoria(categoriaDTO);
                System.out.println("categoria primer snipplet : "+categoriaDTO.getSnipplets().get(0).getTitulo());
                databaseHandler.addSnipplets(categoriaDTO);


            }
        });




        return new CategoriaDTO();

    }

/*
    public String sendToServer() throws IOException {
        String url = configurationService.getUri() + "guardarCategoria";
        CategoriaDTO recuperarGuardado = persistencia.recuperarGuardado(filename);
        UserConfiguration userConfiguration = configurationService.getUserConfiguration();
        SendDTO send = new SendDTO();
        send.setUsername(userConfiguration.getUsername());
        send.setPassword(userConfiguration.getPassword());
        send.setCategoriaDTO(recuperarGuardado);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        if (recuperarGuardado != null) {
            OkHttpClient client = new OkHttpClient();
            ObjectMapper mapper = new ObjectMapper();
            String writeValueAsString = mapper.writeValueAsString(send);
            System.out.println(writeValueAsString);
            RequestBody body = RequestBody.create(JSON, writeValueAsString);

            Request request = new Request.Builder().url(url).post(body).build();
            okhttp3.Response response = client.newCall(request).execute();

            String responseBody = response.body().string();

            Toast.makeText(context, responseBody, Toast.LENGTH_SHORT).show();
            return responseBody;
        } else {

            Toast.makeText(context, "Este archivo no contiene snipplets!", Toast.LENGTH_SHORT).show();
            return "";

        }
    }
    */

}
