package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import ar.com.sourcesistemas.snipplet.AgregarCategoriaActivity;
import ar.com.sourcesistemas.snipplet.CreateNewSnippletActivity;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/22/2016.
 */

public class AddButtonActionListener implements View.OnClickListener {

    private Context context;
    private CategoriaDTO categoriaDTO;

    public AddButtonActionListener(Context context, CategoriaDTO categoriaDTO){
        this.context = context;
        this.categoriaDTO = categoriaDTO;

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, CreateNewSnippletActivity.class);
        intent.putExtra("categoriaDTO",categoriaDTO);
        context.startActivity(intent);
        //databaseHandler.deleteALL();


    }
}
