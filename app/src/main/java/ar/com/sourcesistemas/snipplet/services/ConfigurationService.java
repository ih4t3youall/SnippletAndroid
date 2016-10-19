package ar.com.sourcesistemas.snipplet.services;


import android.widget.Toast;

import java.io.IOException;

import ar.com.sourcesistemas.snipplet.domain.UserConfiguration;

public class ConfigurationService {


    private  UserConfiguration userConfiguration;

    public String getUri() {

        return "http://93.188.167.193:8082/";

    }

    public String getPrefix() {

        return "aprefix";

    }






    public void cambiarHost(String nuevoHost) {

   // cambia el host

    }

    public void cambiarPrefix(String newPrefix) {
       //cambia el prefix

    }

    // TODO doing
    public void cambiarUsuario(UserConfiguration userConfiguration) {

   //cambia el usuario

    }

    public UserConfiguration getUserConfiguration() {

        return userConfiguration;
    }

    public void setUserConfiguration(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }



}
