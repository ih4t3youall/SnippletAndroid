package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.AddButtonActionListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.DeleteCategoryListener;
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
    private Bundle savedInstanceState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.savedInstanceState =savedInstanceState;
        super.onCreate(savedInstanceState);

        snipplets = new ArrayList<View>();

        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.snipplet_container);
        String nombre = getIntent().getExtras().getString("nombre");
        CategoriaDTO categoriaDTO = databaseHandler.getCategoriaDTO(nombre);
        getSnipplets(categoriaDTO);

        Button  add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new AddButtonActionListener(this,new CategoriaDTO(nombre)));

        Button  deleteCategory = (Button)findViewById(R.id.deleteCategory);

        deleteCategory.setOnClickListener(new DeleteCategoryListener(this,categoriaDTO));

    }

    public void onResume() {

        this.onCreate(savedInstanceState);

    }


    public void getSnipplets(CategoriaDTO categoriaDTO){



        databaseHandler.getSnipplet(categoriaDTO);
        LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout_principal);
        for (Snipplet snipplet: categoriaDTO.getSnipplets() ) {

            View snippletContainer= inflater.inflate(R.layout.snipplet_layout, null, false);

            snipplets.add(snippletContainer);

            ((EditText)snippletContainer.findViewById(R.id.titulo)).setText(snipplet.getTitulo());
            ((EditText)snippletContainer.findViewById(R.id.contenido)).setText(snipplet.getContenido());
            ((Button)snippletContainer.findViewById(R.id.editar)).setOnClickListener( new EditarSnippletListener(context,snipplet));

            linearLayout.addView(snippletContainer);

        }





    }

}
