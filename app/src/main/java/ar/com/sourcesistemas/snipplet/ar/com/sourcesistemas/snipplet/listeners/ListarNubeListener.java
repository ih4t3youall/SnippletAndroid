package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.conection.Connector;

/**
 * Created by H4te on 10/19/2016.
 */

public class ListarNubeListener implements View.OnClickListener {

    private Context context;
    private LinearLayout linearLayout;
    public ListarNubeListener(Context context, LinearLayout linearLayout){
        this.linearLayout = linearLayout;
       this.context = context;
    }


    @Override
    public void onClick(View v) {

        Connector connector = new Connector();
        String[] files=null ;
        try {
            files = connector.list();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < files.length; i++){

            Button boton = new Button(context);
            boton.setText(files[i]);
            linearLayout.addView(boton);


        }

    }
}
