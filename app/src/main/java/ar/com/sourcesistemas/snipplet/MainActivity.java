package ar.com.sourcesistemas.snipplet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.SnippletLuncherListener;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

public class MainActivity extends AppCompatActivity  {

    static Context context;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.activity_main);
        List<CategoriaDTO> categoriasDTO = databaseHandler.getAllCategoriasDTO();


        List<Button> buttons = new LinkedList<Button>();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.removeAllViews();

        for (CategoriaDTO categoriaDTO : categoriasDTO) {

            Button button = new Button(context);
            button.setText(categoriaDTO.getNombre());
            SnippletLuncherListener snipletLuncherListener = new SnippletLuncherListener(getApplicationContext(),categoriaDTO.getNombre());
            button.setOnClickListener(snipletLuncherListener);


            layout.addView(button);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem add = menu.findItem(R.id.add);

        add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(getApplicationContext(), AgregarCategoriaActivity.class);
                startActivity(intent);
                //databaseHandler.deleteALL();

                return false;
            }
        });


        MenuItem nube = menu.findItem(R.id.nube);
        nube.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(getApplicationContext(), AdministrarNubeActivity.class);
                startActivity(intent);

                return false;
            }
        });

        MenuItem preferences = menu.findItem(R.id.preferences);
        preferences.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){


            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(getApplicationContext(),PreferencesActivity.class);
                startActivity(intent);

                return false;
            }
        });


        return true;
    }



    public void onResume() {
        super.onResume();



        List<CategoriaDTO> categoriasDTO = databaseHandler.getAllCategoriasDTO();


        List<Button> buttons = new LinkedList<Button>();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.removeAllViews();

        for (CategoriaDTO categoriaDTO : categoriasDTO) {

            Button button = new Button(context);
            button.setText(categoriaDTO.getNombre());
            SnippletLuncherListener snipletLuncherListener = new SnippletLuncherListener(getApplicationContext(),categoriaDTO.getNombre());
            button.setOnClickListener(snipletLuncherListener);


            layout.addView(button);

        }


        /*List<CategoriaDTO> categoriasDTO = databaseHandler.getAllCategoriasDTO();


        List<Button> buttons = new LinkedList<Button>();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.removeAllViews();

        for (CategoriaDTO categoriaDTO : categoriasDTO) {

            Button button = new Button(context);
            button.setText(categoriaDTO.getNombre());
            SnippletLuncherListener snipletLuncherListener = new SnippletLuncherListener(getApplicationContext(),categoriaDTO.getNombre());
            button.setOnClickListener(snipletLuncherListener);


            layout.addView(button);

        }
        databaseHandler.closeDatabase();
        */




    }


    }