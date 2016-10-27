package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import ar.com.sourcesistemas.snipplet.EditSnippletActivity;
import ar.com.sourcesistemas.snipplet.SnippletActivity;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;

/**
 * Created by H4te on 10/21/2016.
 */

public class EditarSnippletListener implements View.OnClickListener {


    private Context context;
    private Snipplet snipplet;


    public EditarSnippletListener(Context context, Snipplet snipplet){

        this.context = context;
        this.snipplet = snipplet;


    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, EditSnippletActivity.class);
        intent.putExtra("snipplet", snipplet);


        context.startActivity(intent);

    }
}
