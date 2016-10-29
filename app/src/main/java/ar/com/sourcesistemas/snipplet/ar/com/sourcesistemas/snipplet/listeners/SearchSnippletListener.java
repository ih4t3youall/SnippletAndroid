package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import ar.com.sourcesistemas.snipplet.SearchResultActivity;
import ar.com.sourcesistemas.snipplet.SnippletActivity;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;

/**
 * Created by juan.m.lequerica on 10/29/2016.
 */

public class SearchSnippletListener implements View.OnClickListener {

    private Context context;
    private Snipplet snipplet;


    public SearchSnippletListener(Context applicationContext, Snipplet snipplet) {

        this.context = applicationContext;
        this.snipplet = snipplet;

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("snipplet", snipplet);
        context.startActivity(intent);


    }
}
