package org.projet_integre.online_book.models.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.projet_integre.online_book.models.Emprunter;
import org.projet_integre.online_book.models.Role;
import org.projet_integre.online_book.models.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("client")
public class Client extends User{
    
    @Column(unique = true)
    private Long matricule;

    public Client(Long id, String nom, String username, String prenom, String email, String password, Long matricule){
        super(id, nom, username, prenom, email, password, Role.CLIENT);
        this.matricule = matricule;
    }


    public Client() {
        super();
    }

    public Long getMatricule() {
        return this.matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

}
