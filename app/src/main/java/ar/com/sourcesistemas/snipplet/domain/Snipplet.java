package ar.com.sourcesistemas.snipplet.domain;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

        import java.io.Serializable;
@JsonIgnoreProperties({"id"})
public class Snipplet implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long id;
    private String titulo;
    private String contenido;

    public Snipplet(){

    }

    public Snipplet(long id, String titulo, String contenido){

        this.titulo = titulo;
        this.contenido = contenido;
        this.id = id;

    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean buscarTexto(String palabra) {

        if (contenido.trim().toLowerCase().indexOf(palabra.trim().toLowerCase()) != -1 || titulo.trim().toLowerCase().indexOf(palabra.trim().toLowerCase()) != -1) {

            return true;
        } else {

            return false;

        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
