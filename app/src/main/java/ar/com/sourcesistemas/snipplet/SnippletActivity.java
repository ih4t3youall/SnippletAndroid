package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/19/2016.
 */

public class SnippletActivity  extends Activity{


    protected Context context ;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.snipplet_container);
        String nombre = getIntent().getExtras().getString("nombre");
        getSnipplets(nombre);

    }


    public void getSnipplets(String nombre){

        CategoriaDTO categoriaDTO = databaseHandler.getCategoriaDTO(nombre);

        databaseHandler.getSnipplet(categoriaDTO);

        Toast.makeText(context, categoriaDTO.getSnipplets().get(0).getTitulo(), Toast.LENGTH_SHORT).show();

        //LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout);




    }

}
