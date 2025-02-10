package org.projet_integre.online_book.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Emprunter {

    @EmbeddedId
    private EmpruntPK id;

    @Column(nullable = false)
    private LocalDate dateEmprunt;
    @Column(nullable = false)  
    private LocalDate dateRetour;

    @ManyToOne
    @MapsId("client_id")
    @JoinColumn(name = "client_id", nullable = false)
    private OurUsers client;

    @ManyToOne
    @MapsId("book_id")
    @JoinColumn(name = "livre_id", nullable = false)
    private Book livre;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    public Emprunter() {}


    public Emprunter(LocalDate dateEmprunt, LocalDate dateRetour, OurUsers client, Book livre) {
        this.id = new EmpruntPK(client.getId(),livre.getIsbn());
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.client = client;
        this.livre = livre;
    }

    public EmpruntPK getId() {
        return this.id;
    }

    public void setId(EmpruntPK id) {
        this.id = id;
    }

    public LocalDate getDateEmprunt() {
        return this.dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return this.dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public OurUsers getClient() {
        return this.client;
    }

    public void setClient(OurUsers client) {
        this.client = client;
    }

    public Book getLivre() {
        return this.livre;
    }

    public void setLivre(Book livre) {
        this.livre = livre;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

}
