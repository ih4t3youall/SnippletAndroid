package ar.com.sourcesistemas.snipplet.dto;


        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

        import ar.com.sourcesistemas.snipplet.domain.Snipplet;

@JsonIgnoreProperties({"idCategoria"})
public class CategoriaDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long idCategoria;
    private String nombre;
    private List<Snipplet> snipplets;
    private List<String> tags;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String nombre) {
        this.nombre = nombre;

    }

    public void addSnipplet(Snipplet snipplet) {
        if (snipplets == null) {

            snipplets = new ArrayList<Snipplet>();

        }

        snipplets.add(snipplet);

    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Snipplet> getSnipplets() {
        return snipplets;
    }

    public void setSnipplets(List<Snipplet> snipplets) {
        this.snipplets = snipplets;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
