package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import ar.com.sourcesistemas.snipplet.MainActivity;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by juan.m.lequerica on 10/27/2016.
 */

public class DeleteCategoryListener implements View.OnClickListener {


    private Context context;
    private CategoriaDTO categoriaDTO;
    private DatabaseHandler databaseHandler;

    public DeleteCategoryListener(Context context, CategoriaDTO categoriaDTO){
        this.context = context;
        this.categoriaDTO =categoriaDTO;
        databaseHandler = new DatabaseHandler(context,null,null,1);

    }


    @Override
    public void onClick(View view) {

        databaseHandler.deleteCategoria(categoriaDTO);
        Intent in = new Intent(context, MainActivity.class);

        context.startActivity(in);



    }
}
