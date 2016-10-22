package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;

/**
 * Created by H4te on 10/21/2016.
 */

public class GuardarSnippletListener implements View.OnClickListener {


    private DatabaseHandler databaseHandler;
    private Snipplet snipplet;
    private Context context;
    private EditText editText;
    private TextView textView;

    public GuardarSnippletListener(Context context, Snipplet snipplet, EditText editText, TextView textView){

        databaseHandler =  new DatabaseHandler(context,null,null,1);
        this.context = context;
        this.snipplet = snipplet;
        this.editText = editText;
        this.textView = textView;

    }

    @Override
    public void onClick(View v) {

        snipplet.setTitulo(editText.getText().toString());
        snipplet.setContenido(textView.getText().toString());

        databaseHandler.saveSnipplet(snipplet);
        Toast.makeText(context, snipplet.getTitulo()+" Guardado con exito", Toast.LENGTH_SHORT).show();


    }
}
