package com.library.demo.model.dtos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataApiOriginal {

    @JsonAlias("results")
    private List<DataBookDto> responseBooks;

    public List<DataBookDto> getResponseBooks(){
        return responseBooks;
    }



}
