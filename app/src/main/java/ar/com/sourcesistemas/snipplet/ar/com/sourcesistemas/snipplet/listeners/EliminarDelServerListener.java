package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.conection.Connector;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/22/2016.
 */

public class EliminarDelServerListener implements View.OnClickListener {

    private CategoriaDTO categoriaDTO;
    private Connector connector;
    private DatabaseHandler databaseHandler;
    private Context context;



    public EliminarDelServerListener(String nombreCategoria,Context context){

        this.categoriaDTO = new CategoriaDTO(nombreCategoria);
        this.connector = new Connector(context);
        this.context = context;
        databaseHandler = new DatabaseHandler(this.context,null,null,1);
    }


    @Override
    public void onClick(View v) {
           try {
            connector.deleteFromServer(categoriaDTO);
        } catch (JsonProcessingException e) {
            Toast.makeText(context, "Error al eliminar la categoria del servidor", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
               e.printStackTrace();
           }


    }


}
