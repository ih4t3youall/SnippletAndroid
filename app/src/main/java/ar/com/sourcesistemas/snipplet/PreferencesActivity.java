package ar.com.sourcesistemas.snipplet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.com.sourcesistemas.snipplet.ar.com.sourcesistemas.snipplet.listeners.UpdatePreferencesListener;
import ar.com.sourcesistemas.snipplet.database.DatabaseHandler;
import ar.com.sourcesistemas.snipplet.domain.Preferences;

/**
 * Created by H4te on 10/24/2016.
 */

public class PreferencesActivity extends Activity{


        private DatabaseHandler databaseHandler;
        private Preferences preferences;
        private EditText password;
        private EditText username;
        private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_layout);
        databaseHandler = new DatabaseHandler(this,null,null,1);
        final Preferences preferences = databaseHandler.getPreferences();
        if(preferences == null){
         this.preferences = new Preferences();
        }else {
            this.preferences = preferences;
        }
        password = (EditText)findViewById(R.id.passwd);
        username = (EditText)findViewById(R.id.username);
        url = (EditText)findViewById(R.id.url);
        Button save = (Button)findViewById(R.id.save);

        if(!(preferences == null)){

            password.setText(preferences.getPasswd());
            username.setText(preferences.getUsername());
            url.setText(preferences.getUri());
        }

        save.setOnClickListener(new UpdatePreferencesListener(this.preferences,username,password,url,this));






    }




}
