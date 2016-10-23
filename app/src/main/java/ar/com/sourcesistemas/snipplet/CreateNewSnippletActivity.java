package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.SaveNewSnippletActionListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/22/2016.
 */

public class CreateNewSnippletActivity extends Activity {


    private String nombre ;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CategoriaDTO categoriaDTO = (CategoriaDTO) getIntent().getExtras().get("categoriaDTO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_snipplet);

        Button save =(Button) findViewById(R.id.guardar);

        databaseHandler = new DatabaseHandler(this,null,null,1);

        save.setOnClickListener(new SaveNewSnippletActionListener(this,categoriaDTO));


    }

    public String getTitulo(){
        return ((EditText)findViewById(R.id.titulo)).getText().toString();

    }

    public String getContenido(){
        return ((EditText)findViewById(R.id.contenido)).getText().toString();

    }

    public CategoriaDTO getCategoriaDTO (){

        CategoriaDTO categoriaDTO = new CategoriaDTO(nombre);
        databaseHandler.getSnipplet(categoriaDTO);
        return categoriaDTO;

    }


}
