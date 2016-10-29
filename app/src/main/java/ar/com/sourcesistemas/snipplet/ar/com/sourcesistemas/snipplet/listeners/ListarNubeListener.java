package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.AdministrarNubeActivity;
import ar.com.sourcesistemas.snipplet.conection.Connector;

/**
 * Created by H4te on 10/19/2016.
 */

public class ListarNubeListener implements View.OnClickListener {

    private AdministrarNubeActivity context;
    private LinearLayout linearLayout;
    private Connector connector;
    public ListarNubeListener(AdministrarNubeActivity context) throws Exception {
        this.linearLayout = linearLayout;
       this.context = context;
        connector = new Connector(context);
    }


    @Override
    public void onClick(View v) {


        String[] files=null ;

        try {
            files = connector.list(linearLayout,context,1);
        } catch (IOException e) {
            Toast.makeText(context, "Error al listar de la nube.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}
