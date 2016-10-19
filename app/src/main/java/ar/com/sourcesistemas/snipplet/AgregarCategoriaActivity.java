package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.AgregarCategoriaListener;

/**
 * Created by H4te on 10/19/2016.
 */

public class AgregarCategoriaActivity extends Activity {

    protected Context context;
    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.agregar_categoria);
        EditText editText = (EditText)findViewById(R.id.texto);

        AgregarCategoriaListener agregarCategoriaListener = new AgregarCategoriaListener(editText,context);



        Button agregar = (Button)findViewById(R.id.agregar);
        agregar.setOnClickListener(agregarCategoriaListener);


        Button cancelar = (Button)findViewById(R.id.cancel);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(context, MainActivity.class);

                startActivity(in);
                setResult(Activity.RESULT_OK);

            }
        });




    }

}
