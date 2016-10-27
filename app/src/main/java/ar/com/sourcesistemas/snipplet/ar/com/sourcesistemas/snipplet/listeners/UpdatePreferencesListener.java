package ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Preferences;

/**
 * Created by H4te on 10/26/2016.
 */

public class UpdatePreferencesListener implements OnClickListener {

    private Preferences preferences;
    private EditText username;
    private EditText password;
    private EditText url;
    private DatabaseHandler databaseHandler;
    private Context context;

    public UpdatePreferencesListener(Preferences preferences, EditText username, EditText password, EditText url,Context context) {
        this.preferences =  preferences;
        this.username = username;
        this.password = password;
        this.url = url;
        this.databaseHandler = new DatabaseHandler(context,null,null,1);
        this.context = context;

    }


    @Override
    public void onClick(View v) {


        url.getText().toString();

        this.preferences.setUsername(username.getText().toString());
        this.preferences.setPasswd(password.getText().toString());
        this.preferences.setUri(url.getText().toString());

        databaseHandler.savePreferences(preferences);
        Toast.makeText(context, "Datos actualizados con exito.", Toast.LENGTH_SHORT).show();

    }
}
