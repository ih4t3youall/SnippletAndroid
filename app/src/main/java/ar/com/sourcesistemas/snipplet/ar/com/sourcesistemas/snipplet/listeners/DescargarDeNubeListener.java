package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.util.Log;
import android.view.View;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.conection.Connector;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/19/2016.
 */

public class DescargarDeNubeListener implements View.OnClickListener{


    private String nombreCategoria;
    private Connector connector;

    public DescargarDeNubeListener(String nombreCategoria){

        this.nombreCategoria = nombreCategoria;
        this.connector = new Connector();
    }


    @Override
    public void onClick(View v) {

        CategoriaDTO categoriaDTO = null;
        try {
             categoriaDTO = connector.getFromServer(nombreCategoria);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
