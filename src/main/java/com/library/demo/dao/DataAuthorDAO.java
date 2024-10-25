package com.library.demo.dao;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataAuthorDAO {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCumpleanios() {
        return cumpleanios;
    }

    public void setCumpleanios(Integer cumpleanios) {
        this.cumpleanios = cumpleanios;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

}
