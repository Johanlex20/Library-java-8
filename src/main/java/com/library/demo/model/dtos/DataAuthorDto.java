package com.library.demo.model.dtos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataAuthorDto {
    @JsonAlias("name") String nombre;
    @JsonAlias("birth_year") Integer cumpleanios;
    @JsonAlias("death_year") Integer fechaFallecimiento;


    @Override
    public String toString() {
        return "Autor: " + nombre +
                " Nacio: " + cumpleanios +
                ", Fallecio: " + (fechaFallecimiento != null ? fechaFallecimiento : "N/A") + ")";
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public Integer getCumpleanios() {
        return cumpleanios;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }


}
