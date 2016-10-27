package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;

import ar.com.sourcesistemas.snipplet.conection.Connector;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/22/2016.
 */

public class BorrarNubeListener implements View.OnClickListener {

    private Context context;
    private CategoriaDTO categoriaDTO;
    private Connector connector;

    public BorrarNubeListener(Context context,String categoria) throws Exception {

        this.context = context;
        this.categoriaDTO = new CategoriaDTO(categoria);


            this.connector = new Connector(context);


    }


    @Override
    public void onClick(View v) {


        /*try {
            connector.deleteFromServer(categoriaDTO.getNombre());
        } catch (JsonProcessingException e) {
            Toast.makeText(context, "Error al eliminar la categoria del servidor", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }*/

    }
}
