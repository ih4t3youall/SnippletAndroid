package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.AddButtonActionListener;
import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.DeleteCategoryListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

/**
 * Created by juan.m.lequerica on 10/29/2016.
 */

public class SearchResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Snipplet snipplet =(Snipplet) getIntent().getExtras().get("snipplet");
        Toast.makeText(this, snipplet.getTitulo(), Toast.LENGTH_SHORT).show();

    }


}
