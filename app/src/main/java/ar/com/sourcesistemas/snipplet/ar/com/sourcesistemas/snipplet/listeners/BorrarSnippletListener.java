package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/26/2016.
 */

public class BorrarSnippletListener implements View.OnClickListener {

    private Context context;
    private DatabaseHandler databaseHandler;
    private Snipplet snipplet;


    public BorrarSnippletListener(Context context,Snipplet snipplet){

        this.context = context;
        databaseHandler = new DatabaseHandler(context,null,null,6);
        this.snipplet = snipplet;



    }


    @Override
    public void onClick(View v) {



        databaseHandler.deleteSnipplet(snipplet);





    }
}
