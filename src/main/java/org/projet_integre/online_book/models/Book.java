package org.projet_integre.online_book.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livres")
public class Book {

    @Id
    private String isbn;
    @Column(unique = true)
    private String title;
    private int nbrExemplaireAvai;
    @Nonnull
    private String author;
    private String cover_image;

    @ManyToOne
    @JoinColumn(name = "categorie", nullable = false)
    private Categorie categorie;

    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private List<Emprunter> prets = new ArrayList<>();

    public Book(String isbn, String title, String author, String cover_image, Categorie categorie) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.cover_image = cover_image;
        this.categorie = categorie;
    }

    public Book(){}
 

    public List<Emprunter> getPrets() {
        return this.prets;
    }

    public void setPrets(List<Emprunter> prets) {
        this.prets = prets;
    }


    public int getNbrExemplaireAvai() {
        return this.nbrExemplaireAvai;
    }

    public void setNbrExemplaireAvai(int nbrExemplaireAvai) {
        this.nbrExemplaireAvai = nbrExemplaireAvai;
    }


    public String getTitle() {
        return this.title;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCover_image() {
        return this.cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

}
