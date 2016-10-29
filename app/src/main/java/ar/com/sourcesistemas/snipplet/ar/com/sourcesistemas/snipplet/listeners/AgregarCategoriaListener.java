package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ar.com.sourcesistemas.snipplet.MainActivity;
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
        String mensaje = editText.getText().toString();
        if(!mensaje.equals("")) {
            categoriaDTO.setNombre(editText.getText().toString());

            databaseHandler.addCategoria(categoriaDTO);

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);


        }else{

            Toast.makeText(context, "Debe escribir una categoria.", Toast.LENGTH_SHORT).show();

        }


    }
}
