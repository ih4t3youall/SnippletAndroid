package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/19/2016.
 */

public class AgregarCategoriaListener implements View.OnClickListener {


    protected EditText editText;
    protected Context context;
    private DatabaseHandler databaseHandler;

    public AgregarCategoriaListener(EditText editText, Context context) {
        this.editText = editText;
        this.context = context;

         databaseHandler = new DatabaseHandler(context, null, null, 1);

    }

    @Override
    public void onClick(View v) {

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre(editText.getText().toString());

        databaseHandler.addCategoria(categoriaDTO);



    }
}
