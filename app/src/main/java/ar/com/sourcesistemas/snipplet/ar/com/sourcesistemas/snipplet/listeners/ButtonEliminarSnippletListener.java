package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.AdministrarNubeActivity;

import ar.com.sourcesistemas.snipplet.conection.Connector;

/**
 * Created by H4te on 10/22/2016.
 */
public class ButtonEliminarSnippletListener implements View.OnClickListener {

    private AdministrarNubeActivity context;
    private LinearLayout linearLayout;
    private Connector connector = null;
    public ButtonEliminarSnippletListener(AdministrarNubeActivity context) throws Exception {

        this.context = context;

            connector = new Connector(context);

    }


    @Override
    public void onClick(View v) {





        String[] files=null ;


        try {
            files = connector.list(linearLayout,context,0);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}