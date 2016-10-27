package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.ButtonUploadListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.DescargarDeNubeListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.ButtonEliminarSnippletListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.EliminarDelServerListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.ListarNubeListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;

/**
 * Created by H4te on 10/19/2016.
 */

public class AdministrarNubeActivity extends Activity {

    private Context context;
    private DatabaseHandler databaseHandler;
    private Button listar;
    public LinearLayout linearLayout =null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        inicio();


    }

    public void inicio(){

        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.administrar_nube);

        listar =  (Button)findViewById(R.id.listar);

        Button download = (Button) findViewById(R.id.upload);
        Button borrar = (Button) findViewById(R.id.borrar);
        Button upload = (Button) findViewById(R.id.upload);

        linearLayout = (LinearLayout)findViewById(R.id.layoutLista);


        try {
            listar.setOnClickListener(new ListarNubeListener(this));
            borrar.setOnClickListener(new ButtonEliminarSnippletListener(this));
        } catch (Exception e) {
            Toast.makeText(context, "No hay configuracion de usuario", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(context, MainActivity.class);

            startActivity(in);
            setResult(Activity.RESULT_OK);
        }
        upload.setOnClickListener(new ButtonUploadListener(this));

    }

    public void setLista(final String[] directorios,final int eliminar) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                linearLayout.removeAllViews();
                for (String string : directorios) {
                    Button button = new Button(context);
                    button.setText(string);
                    linearLayout.addView(button);


                    try {
                        switch (eliminar) {

                            case 0:
                                button.setOnClickListener(new EliminarDelServerListener(string, context));
                                break;
                            case 1:

                                button.setOnClickListener(new DescargarDeNubeListener(string, context));

                                break;
                        }


                    } catch (Exception e) {
                        Toast.makeText(context, "No hay configuracion de usuario", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(context, MainActivity.class);

                        startActivity(in);
                        setResult(Activity.RESULT_OK);
                    }

                }
            }
        });







    }
}
