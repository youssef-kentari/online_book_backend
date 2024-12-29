package org.projet_integre.online_book.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ClientBookPK implements Serializable{
    @Column(name = "client_id")
    Long client_id;

    @Column(name = "livre_id")
    String livre_id;


    public ClientBookPK(Long client_id, String livre_id) {
        this.client_id = client_id;
        this.livre_id = livre_id;
    }
    
    public ClientBookPK() {}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientBookPK that = (ClientBookPK) o;
        return Objects.equals(client_id, that.client_id) && Objects.equals(livre_id, that.livre_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id, livre_id);
    }
}
