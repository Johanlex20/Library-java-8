package com.library.demo.model.dtos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataAuthorDto {
    @JsonAlias("name") String nombre;
    @JsonAlias("birth_year") Integer fechaNacimiento;
    @JsonAlias("death_year") Integer fechaFallecimiento;



    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }


}
