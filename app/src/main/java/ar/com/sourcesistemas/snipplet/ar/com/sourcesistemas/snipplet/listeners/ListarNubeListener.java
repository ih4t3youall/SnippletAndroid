package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.AdministrarNubeActivity;
import ar.com.sourcesistemas.snipplet.conection.Connector;

/**
 * Created by H4te on 10/19/2016.
 */

public class ListarNubeListener implements View.OnClickListener {

    private AdministrarNubeActivity context;
    private LinearLayout linearLayout;
    public ListarNubeListener(AdministrarNubeActivity context){
        this.linearLayout = linearLayout;
       this.context = context;
    }


    @Override
    public void onClick(View v) {

        Connector connector = new Connector(context);
        String[] files=null ;

        try {
            files = connector.list(linearLayout,context,1);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
