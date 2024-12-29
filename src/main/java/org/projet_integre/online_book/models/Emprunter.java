package org.projet_integre.online_book.models;

import java.time.LocalDate;
import org.projet_integre.online_book.models.users.Client;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Emprunter {

    @EmbeddedId
    private ClientBookPK id;

    private LocalDate dateEmprunt; // Utilisation de LocalDate pour éviter les problèmes de fuseaux horaires
    private LocalDate dateRetour;

    @ManyToOne
    @MapsId("client_id")
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @MapsId("livre_id")
    @JoinColumn(name = "livre_id", nullable = false)
    private Book livre;

    public Emprunter(Client client, Book livre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.id = new ClientBookPK(client.getId(), livre.getIsbn());
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.client = client;
        this.livre = livre;
    }

    public Emprunter() {}

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getLivre() {
        return livre;
    }

    public void setLivre(Book livre) {
        this.livre = livre;
    }

    public ClientBookPK getId() {
        return id;
    }

    public void setId(ClientBookPK id) {
        this.id = id;
    }
}
