package org.projet_integre.online_book.models.users;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import org.projet_integre.online_book.models.Role;
import org.projet_integre.online_book.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("biblio")
public class Bibliothecaire extends User{

    @Column(unique = true)
    private Long tocken;

    public Bibliothecaire(Long id, String nom, String username, String prenom, String email, String password, Long tocken){
        super(id, nom, username, prenom, email, password, Role.ADMIN);
        this.tocken = tocken;
    }

    public Bibliothecaire(){}

    public Long getTocken() {
        return this.tocken;
    }

    public void setTocken(Long tocken) {
        this.tocken = tocken;
    }

}
