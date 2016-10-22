package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.EditarSnippletListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/19/2016.
 */

public class SnippletActivity  extends Activity{


    protected Context context ;
    private DatabaseHandler databaseHandler;
    private List<View> snipplets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        snipplets = new ArrayList<View>();

        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.snipplet_container);


    }


    public void getSnipplets(String nombre,LinearLayout linearLayout){

        CategoriaDTO categoriaDTO = databaseHandler.getCategoriaDTO(nombre);

        databaseHandler.getSnipplet(categoriaDTO);
        LayoutInflater inflater = LayoutInflater.from(context);


        for (Snipplet snipplet: categoriaDTO.getSnipplets() ) {

            View snippletContainer= inflater.inflate(R.layout.snipplet_layout, null, false);

            snipplets.add(snippletContainer);

            ((EditText)snippletContainer.findViewById(R.id.titulo)).setText(snipplet.getTitulo());
            ((EditText)snippletContainer.findViewById(R.id.contenido)).setText(snipplet.getContenido());
            ((Button)snippletContainer.findViewById(R.id.editar)).setOnClickListener( new EditarSnippletListener(context,snipplet));

            linearLayout.addView(snippletContainer);

        }



    }

    public void onResume() {

        super.onResume();
        String nombre = getIntent().getExtras().getString("nombre");
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout_principal);
        linearLayout.removeAllViews();
        getSnipplets(nombre,linearLayout);
    }


}
