package org.projet_integre.online_book.models.users;

import org.projet_integre.online_book.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Administrateur extends User{

    @Column(unique = true)
    private Long tocken;

    public Administrateur(Long id, String nom, String prenom,String email, String password, Long tocken){
        super(id, nom, prenom, email, password);
        this.tocken = tocken;
    }

    public Administrateur(){}

    public Long getTocken() {
        return this.tocken;
    }

    public void setTocken(Long tocken) {
        this.tocken = tocken;
    }

}
