package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.ListarNubeListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;

/**
 * Created by H4te on 10/19/2016.
 */

public class AdministrarNubeActivity extends Activity {

    private Context context;
    private DatabaseHandler databaseHandler;
    private Button listar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.administrar_nube);

        listar =  (Button)findViewById(R.id.listar);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout);
        listar.setOnClickListener(new ListarNubeListener(this,linearLayout));

    }
}
