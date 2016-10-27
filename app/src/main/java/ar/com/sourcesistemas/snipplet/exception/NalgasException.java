package ar.com.sourcesistemas.snipplet.exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by H4te on 10/26/2016.
 */

public class NalgasException extends Exception{


    public NalgasException(Context context){
        String mensaje ="No tenes cargadas las preferencias!";


        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();


    }

}
