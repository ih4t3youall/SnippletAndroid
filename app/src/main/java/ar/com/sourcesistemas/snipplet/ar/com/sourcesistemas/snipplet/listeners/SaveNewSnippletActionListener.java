package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;

import ar.com.sourcesistemas.snipplet.CreateNewSnippletActivity;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/22/2016.
 */

public class SaveNewSnippletActionListener implements View.OnClickListener {


    private CreateNewSnippletActivity context;
    private DatabaseHandler databaseHandler;
    private CategoriaDTO categoriaDTO;

    public SaveNewSnippletActionListener(CreateNewSnippletActivity createNewSnippletActivity, CategoriaDTO categoriaDTO) {
        context = createNewSnippletActivity;
        this.categoriaDTO = categoriaDTO;
        databaseHandler = new DatabaseHandler(context,null,null,1);
    }


    @Override
    public void onClick(View v) {

        String titulo = context.getTitulo();
        String contenido = context.getContenido();
        databaseHandler.insertNewSnipplet(categoriaDTO,titulo,contenido);

    }
}
