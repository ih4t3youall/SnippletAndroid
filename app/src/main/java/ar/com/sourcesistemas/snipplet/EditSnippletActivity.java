package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.BorrarSnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.ButtonEliminarSnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.CopySnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.EditarSnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.GuardarSnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.ListarNubeListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by H4te on 10/21/2016.
 */

public class EditSnippletActivity extends Activity {


    private Context context;
    private DatabaseHandler databaseHandler;
    private Snipplet snipplet;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.snipplet= (Snipplet)getIntent().getExtras().get("snipplet");


        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.edit_snipplet);
        EditText editText = (EditText)findViewById(R.id.titulo);
        final TextView textView = (TextView)findViewById(R.id.contenido);
        editText.setText(snipplet.getTitulo());
        textView.setText(snipplet.getContenido());
        Button guardar = (Button)findViewById(R.id.guardar);
        guardar.setOnClickListener(new GuardarSnippletListener(context,snipplet,editText,textView));


        Button borrarSnipplet = (Button)findViewById(R.id.borrarSnipplet);

        borrarSnipplet.setOnClickListener(new BorrarSnippletListener(context,snipplet));

        Button copy = (Button)findViewById(R.id.copy);
        copy.setOnClickListener(new CopySnippletListener(context,editText,textView));
        /*copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(snipplet.getTitulo(), textView.getText().toString());
                clipboard.setPrimaryClip(clip);


            }
        });*/



    }



}
