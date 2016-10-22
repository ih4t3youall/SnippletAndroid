package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

import ar.com.sourcesistemas.snipplet.AdministrarNubeActivity;
import ar.com.sourcesistemas.snipplet.R;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/22/2016.
 */

public class ButtonUploadListener implements View.OnClickListener {

    private AdministrarNubeActivity context;
    private DatabaseHandler databaseHandler;

    public ButtonUploadListener(AdministrarNubeActivity context){
        this.context = context;
        this.databaseHandler = new DatabaseHandler(context,null,null,1);

    }


    @Override
    public void onClick(View v) {


        List<CategoriaDTO> categoriasDTO = databaseHandler.getAllCategoriasDTO();


        List<Button> buttons = new LinkedList<Button>();

        LinearLayout layout = (LinearLayout) context.findViewById(R.id.layoutLista);
        layout.removeAllViews();

        for (CategoriaDTO categoriaDTO : categoriasDTO) {

            Button button = new Button(context);
            button.setText(categoriaDTO.getNombre());
            //SnippletLuncherListener snipletLuncherListener = new SnippletLuncherListener(getApplicationContext(),categoriaDTO.getNombre());
            button.setOnClickListener(new UploadActionListener(context.getApplicationContext(),categoriaDTO.getNombre()));


            layout.addView(button);

        }

    }
}
