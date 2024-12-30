package org.projet_integre.online_book.models.users;

import java.util.ArrayList;
import java.util.List;

import org.projet_integre.online_book.models.Emprunter;
import org.projet_integre.online_book.models.User;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends User{
    
    @Column(unique = true)
    private Long matricule;

    @JsonBackReference
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Emprunter> prets = new ArrayList<>();

    public Client(Long id, String nom, String prenom,String email, String password, Long matricule){
        super(id, nom, prenom,email,password);
        this.matricule = matricule;
    }


    public Client() {
        super();
    }


    public List<Emprunter> getPrets() {
        return this.prets;
    }

    public void setPrets(List<Emprunter> prets) {
        this.prets = prets;
    }

    public Long getMatricule() {
        return this.matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

}
