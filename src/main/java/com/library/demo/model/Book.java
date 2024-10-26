package com.library.demo.model;
import com.library.demo.model.dtos.DataAuthorDto;
import com.library.demo.model.dtos.DataBookDto;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long libroId;

    private String title;

    private Long isbn;

    @OneToOne(cascade = CascadeType.ALL)
    private Author author;

    private String category;

    private boolean available;

    private LocalDate publicationDate;

    private BigDecimal price;

    @ElementCollection
    private List<String> idioma;

    private String formats; // hacer referencia url img

    private Long cantidadDescargas;


    public Book(){}

    public Book(DataBookDto dataBookDto, Author author){
        this.libroId = dataBookDto.getLibroId();
        this.title = dataBookDto.getTitulo();
        this.isbn = dataBookDto.getLibroId();
        this.author = author;
        this.category = dataBookDto.getGenero().stream()
                .map(g->g.split("--"))
                .filter(parts ->parts.length > 1)
                .map(parts ->parts[1].trim())
                .findFirst()
                .orElse("Sin Categoria");
        this.available = true;
        this.publicationDate = LocalDate.now();
        this.price = BigDecimal.valueOf(0);
        this.idioma = dataBookDto.getIdioma();
        this.formats = dataBookDto.getFormats();
        this.cantidadDescargas = dataBookDto.getCantidadDescargas();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    public Long getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Long cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public Author getAuthor() {
        return author;
    }
}
