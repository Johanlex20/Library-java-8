package com.library.demo.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.demo.dao.DataBookDAO;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataApiOriginal {

    @JsonAlias("results")
    private List<DataBookDAO> responseBooks;

    public List<DataBookDAO> getResponseBooks(){
        return responseBooks;
    }

    public void setResponseBooks(List<DataBookDAO> responseBooks){
        this.responseBooks = responseBooks;
    }

}
