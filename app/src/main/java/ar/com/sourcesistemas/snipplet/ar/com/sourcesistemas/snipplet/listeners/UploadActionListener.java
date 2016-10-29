package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.conection.Connector;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/22/2016.
 */

public class UploadActionListener implements View.OnClickListener {


    private Context context;
    private String nombre;
    private Connector connector;
    private DatabaseHandler databaseHandler;

    public UploadActionListener(Context applicationContext, String nombre) throws Exception {
        this.context = applicationContext;
        this.nombre = nombre;
        this.connector = new Connector(applicationContext);
        this.databaseHandler = new DatabaseHandler(context,null,null,1);
    }

    @Override
    public void onClick(View v) {

        CategoriaDTO categoriaDTO = databaseHandler.getCategoriaDTO(nombre);
        databaseHandler.getSnipplet(categoriaDTO);
        try {
            connector.sendToServer(categoriaDTO);
            Toast.makeText(context, "Enviado al servidor.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error al enviar al servidor.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}
