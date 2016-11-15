package ar.com.sourcesistemas.snipplet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;


import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.SearchSnippletListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.SnippletLuncherListener;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

public class MainActivity extends AppCompatActivity  {

    static Context context;
    private DatabaseHandler databaseHandler;
    private List<CategoriaDTO> categoriasDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHandler = new DatabaseHandler(this,null,null,1);
        context = this;
        setContentView(R.layout.activity_main);
        this.categoriasDTO = databaseHandler.getAllCategoriasDTO();

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
        preferences.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(getApplicationContext(), PreferencesActivity.class);
                startActivity(intent);

                return false;
            }
        });


        MenuItem search = menu.findItem(R.id.search);
        search.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @SuppressLint("WrongViewCast")
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);


                //Switch switch =(Switch) promptsView.findViewById(R.id.switch1);

                final Switch toogle = (Switch) promptsView.findViewById(R.id.switch1);





                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text


                                        Boolean buscarCategoria = toogle.isChecked();

                                        if(buscarCategoria){

                                         buscarCategoria(userInput.getText().toString());
                                        }else{
                                        buscar(userInput.getText().toString());
                                        }


                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                return false;
            }
        });

        return true;
    }


    public void buscarCategoria(String buscar){


       List<CategoriaDTO> categoriasDTO = databaseHandler.getAllCategoriasDTO();

        StringTokenizer st = new StringTokenizer(buscar);

        List<CategoriaDTO> categoriasSearch = new LinkedList<CategoriaDTO>();

        while(st.hasMoreElements()){

            String token = st.nextToken();

            for (CategoriaDTO categoriaDTO : categoriasDTO  ) {

                if(categoriaDTO.getNombre().trim().toLowerCase().indexOf(token) != -1){
                    categoriasSearch.add(categoriaDTO);
                }

            }



        }

        reloadList(categoriasSearch);



    }

    public void buscar(String buscar){

        List<Snipplet> snipplets = databaseHandler.getAllSnipplets();
        List<Snipplet> resultados = new LinkedList<Snipplet>();

        for (Snipplet snipplet: snipplets ) {

            if(snipplet.buscarTexto(buscar))
                resultados.add(snipplet);

        }

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.removeAllViews();

        for (Snipplet snipplet : resultados) {

            Button button = new Button(context);
            button.setText(snipplet.getTitulo());

            SearchSnippletListener snipletLuncherListener = new SearchSnippletListener(this,snipplet);
            button.setOnClickListener(snipletLuncherListener);

            layout.addView(button);

        }

    }

    public void reloadList(List<CategoriaDTO> nuevasCategoriasDTO){

        this.categoriasDTO = nuevasCategoriasDTO;
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

    public void onResume() {
        super.onResume();

        List<CategoriaDTO> categoriasDTO = databaseHandler.getAllCategoriasDTO();



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

    }