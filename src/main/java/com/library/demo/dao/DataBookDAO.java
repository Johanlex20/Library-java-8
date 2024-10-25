package com.library.demo.dao;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBookDAO {

    @JsonAlias("id") Long libroId;
    @JsonAlias("title") String titulo;
    @JsonAlias("authors") List<DataAuthorDAO> autor;
    @JsonAlias("subjects")  List<String> genero;
    @JsonAlias("languages") List<String> idioma;
    @JsonAlias("formats") private Map<String, String> formats;
    @JsonAlias("download_count") Long cantidadDescargas;


    // Constructor
    public DataBookDAO() {

    }

    // Getters y Setters
    public Long getLibroId() {
        return libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<DataAuthorDAO> getAutor() {
        return autor;
    }

    public List<String> getGenero() {
        return genero;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public Long getCantidadDescargas() {
        return cantidadDescargas;
    }

    public String getFormats() {
        if (formats != null && formats.containsKey("image/jpeg")) {
            return formats.get("image/jpeg");
        }
        return "No hay imagen disponible";
    }

}
