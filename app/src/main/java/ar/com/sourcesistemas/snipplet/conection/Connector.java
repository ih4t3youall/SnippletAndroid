package ar.com.sourcesistemas.snipplet.conection;

import android.content.Context;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.stream.Collectors;


import ar.com.sourcesistemas.snipplet.AdministrarNubeActivity;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Preferences;

import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

import ar.com.sourcesistemas.snipplet.dto.SendDTO;

import ar.com.sourcesistemas.snipplet.exception.NalgasException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Connector {
private Context context;



    final OkHttpClient client = new OkHttpClient();
    private String[] directorios = null;
    private DatabaseHandler databaseHandler;
    private Preferences preferences;

    public Connector(Context context) throws Exception {

        databaseHandler = new DatabaseHandler(context,null,null,1);
        preferences = databaseHandler.getPreferences();
            if(preferences == null){
                throw new NalgasException(context);

            }

        }


    public String[] list(final LinearLayout linearLayout,final AdministrarNubeActivity context,final int eliminar) throws IOException {

        String url = preferences.getUri() + "listarServer";

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
                Log.d("failure","failure");
                Toast.makeText(null,"Fallo al conectarse con el servidor",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                String responseBody = response.body().string();

                Log.d("RESPUESTA",responseBody);
                directorios = mapper.readValue(responseBody, String[].class);

                context.setLista(directorios,eliminar);

                response.close();


            }
        });


        return directorios;
    }






    public void deleteFromServer(CategoriaDTO categoriaDTO) throws IOException {
        String url = preferences.getUri() + "deleteCategory";

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

        String url = preferences.getUri() + "returnCategory";
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

                Toast.makeText(context, "Error al conectarse al servidor.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error al conectarse al servidor.", Toast.LENGTH_SHORT).show();
                    throw new IOException("Unexpected code " + response);
                }

                String responseBody = response.body().string();
                SendDTO sendDTO  =null;
                try {
                    sendDTO = mapper.readValue(responseBody, SendDTO.class);
                }catch(Exception e ){
                    e.printStackTrace();
                }
                databaseHandler.addCategoria(sendDTO.getCategoriaDTO());
                databaseHandler.addSnipplets(sendDTO.getCategoriaDTO());


            }
        });




        return new CategoriaDTO();

    }


    public String sendToServer(CategoriaDTO categoriaDTO) throws IOException {
        String url = preferences.getUri() + "guardarCategoria";


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

                    SendDTO sendDTO = mapper.readValue(responseBody, SendDTO.class);
                    System.out.println("categoria nombre: "+sendDTO.getCategoriaDTO().getNombre());
                    System.out.println("Borro la categoria DTO");
                    databaseHandler.deleteCategoria(sendDTO.getCategoriaDTO());
                    System.out.println("La creo y la guardo");
                    databaseHandler.addCategoria(sendDTO.getCategoriaDTO());
                    databaseHandler.addSnipplets(sendDTO.getCategoriaDTO());

                }
            });
            //databaseHandler.deleteCategoria();





            return "";
        } else {

            Toast.makeText(context, "Este archivo no contiene snipplets!", Toast.LENGTH_SHORT).show();
            return "";

        }
    }


}
