package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import ar.com.sourcesistemas.snipplet.SnippletActivity;

/**
 * Created by H4te on 10/19/2016.
 */

public class SnippletLuncherListener  implements View.OnClickListener{
    private Context context ;
    private String buttonName;
    public SnippletLuncherListener(Context applicationContext,String buttonName) {
        this.context = applicationContext;
        this.buttonName = buttonName;

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, SnippletActivity.class);
        intent.putExtra("nombre", buttonName);
        context.startActivity(intent);

    }
}
