package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ar.com.sourcesistemas.snipplet.SearchResultActivity;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by juan.m.lequerica on 10/29/2016.
 */

public class CopySnippletListener implements View.OnClickListener {


    private TextView titulo;
    private TextView contenido;
    private Context context;

    public CopySnippletListener(Context context, TextView titulo, TextView contenido) {
        this.contenido = contenido;
        this.titulo = titulo;
        this.context = context;
    }



    @Override
    public void onClick(View view) {

        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(titulo.getText().toString(), contenido.getText().toString());
        clipboard.setPrimaryClip(clip);

    }
}
