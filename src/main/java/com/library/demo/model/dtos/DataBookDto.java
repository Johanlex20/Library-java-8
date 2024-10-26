package com.library.demo.model.dtos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBookDto {

    @JsonAlias("id") Long libroId;
    @JsonAlias("title") String titulo;
    @JsonAlias("authors") List<DataAuthorDto> autores;
    @JsonAlias("subjects")  List<String> genero;
    @JsonAlias("languages") List<String> idioma;
    @JsonAlias("formats") private Map<String, String> formats;
    @JsonAlias("download_count") Long cantidadDescargas;


    public DataBookDto() {
    }

    public Long getLibroId() {
        return libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<DataAuthorDto> getAutores() {
        return autores;
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
