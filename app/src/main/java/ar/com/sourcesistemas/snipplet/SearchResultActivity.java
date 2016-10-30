package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.BorrarSnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.CopySnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.GuardarSnippletListener;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;

import static ar.com.sourcesistemas.snipplet.MainActivity.context;

/**
 * Created by juan.m.lequerica on 10/29/2016.
 */

public class SearchResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Snipplet snipplet =(Snipplet) getIntent().getExtras().get("snipplet");
        setContentView(R.layout.edit_snipplet);

        EditText titulo = (EditText)findViewById(R.id.titulo);
        EditText contenido = (EditText)findViewById(R.id.contenido);

        titulo.setText(snipplet.getTitulo());
        contenido.setText(snipplet.getContenido());

        Button guardar = (Button)findViewById(R.id.guardar);
        Button borrarSnipplet = (Button)findViewById(R.id.borrarSnipplet);
        Button copy = (Button)findViewById(R.id.copy);

        guardar.setOnClickListener(new GuardarSnippletListener(this,snipplet,titulo,contenido));
        borrarSnipplet.setOnClickListener(new BorrarSnippletListener(this,snipplet));
        copy.setOnClickListener(new CopySnippletListener(this,titulo,contenido));

    }


    @Override
    public void onBackPressed() {

        Toast.makeText(this, "ASasdasd", Toast.LENGTH_SHORT).show();
        finish();


    }

}
