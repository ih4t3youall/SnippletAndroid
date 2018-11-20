package ar.com.sourcesistemas.snipplet.domain;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

        import java.io.Serializable;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.StringTokenizer;

@JsonIgnoreProperties({"id"})
public class Snipplet implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long id;
    private String titulo;
    private String contenido;
    private String nombreCategoria;

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



    public boolean buscarTexto(String palabrasAUX ){

        StringTokenizer token = new StringTokenizer(palabrasAUX);
        List<String> palabras = new LinkedList<String>();

        while(token.hasMoreTokens()){
            palabras.add(token.nextToken());
        }



        int cantidad = palabras.size();
        int contador = 0;

        for (String palabra :palabras ) {

            boolean flag = false;
            if(contenido.trim().toLowerCase().indexOf(palabra.toLowerCase()) != -1){
                flag = true;

            }
            if(titulo.trim().toLowerCase().indexOf(palabra.toLowerCase()) != -1){
                flag = true;

            }

            if(flag){
                contador++;
                flag = false;
            }

        }

        if(contador == cantidad){
            return true;
        }else{
            return false;
        }


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
