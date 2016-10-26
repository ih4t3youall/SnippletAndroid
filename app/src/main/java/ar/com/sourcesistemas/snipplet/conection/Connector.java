package ar.com.sourcesistemas.snipplet.conection;

import android.content.Context;


import android.widget.LinearLayout;
import android.widget.Toast;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;



import ar.com.sourcesistemas.snipplet.AdministrarNubeActivity;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Preferences;

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
    private Preferences preferences;

    public Connector(Context context){

        databaseHandler = new DatabaseHandler(context,null,null,1);
        preferences = databaseHandler.getPreferences();
    }


    public String[] list(final LinearLayout linearLayout,final AdministrarNubeActivity context,final int eliminar) throws IOException {
        configurationService = new ConfigurationService();
        String url = configurationService.getUri() + "listarServer";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");



        SendDTO send = new SendDTO();
        send.setUsername(preferences.getUsername());
        send.setPassword(preferences.getPasswd());


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

                context.setLista(directorios,eliminar);

                response.close();


            }
        });


        return directorios;
    }






    public void deleteFromServer(CategoriaDTO categoriaDTO) throws IOException {
        String url = configurationService.getUri() + "deleteCategory";

        SendDTO send = new SendDTO();

        send.setUsername(preferences.getUsername());
        send.setPassword(preferences.getPasswd());
        send.setCategoriaDTO(categoriaDTO);


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();
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





                response.close();


            }
        });


    }


    public CategoriaDTO getFromServer(String nombreCategoria) throws IOException {

        String url = configurationService.getUri() + "returnCategory";
        CategoriaDTO recuperarGuardado = new CategoriaDTO();
        recuperarGuardado.setNombre(nombreCategoria);

        SendDTO send = new SendDTO();

        send.setUsername(preferences.getUsername());
        send.setPassword(preferences.getPasswd());
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


    public String sendToServer(CategoriaDTO categoriaDTO) throws IOException {
        String url = configurationService.getUri() + "guardarCategoria";


        SendDTO send = new SendDTO();
        send.setUsername(preferences.getUsername());
        send.setPassword(preferences.getPasswd());
        send.setCategoriaDTO(categoriaDTO);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        if (categoriaDTO != null) {
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





            return "";
        } else {

            Toast.makeText(context, "Este archivo no contiene snipplets!", Toast.LENGTH_SHORT).show();
            return "";

        }
    }


}
