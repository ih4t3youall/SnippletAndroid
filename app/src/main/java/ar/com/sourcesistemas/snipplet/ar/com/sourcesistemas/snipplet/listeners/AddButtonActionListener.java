package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import ar.com.sourcesistemas.snipplet.AgregarCategoriaActivity;

/**
 * Created by H4te on 10/22/2016.
 */

public class AddButtonActionListener implements View.OnClickListener {

    private Context context;

    public AddButtonActionListener(Context context){
        this.context = context;

    }

    @Override
    public void onClick(View v) {




        Intent intent = new Intent(context, AgregarCategoriaActivity.class);
        context.startActivity(intent);
        //databaseHandler.deleteALL();


    }
}
