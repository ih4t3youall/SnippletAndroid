package ar.com.sourcesistemas.snipplet.domain;

/**
 * Created by H4te on 10/24/2016.
 */

public class Preferences {


    private long id;
    private String username;
    private String passwd;
    private String uri;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
