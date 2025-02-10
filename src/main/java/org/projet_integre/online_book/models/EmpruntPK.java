package org.projet_integre.online_book.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmpruntPK implements Serializable {
    private Long client_id;
    private String book_id;

    public EmpruntPK() {
    }

    public EmpruntPK(Long client_id, String book_id) {
        this.client_id = client_id;
        this.book_id = book_id;
    }

    public Long getClient_id() {
        return this.client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getBook_id() {
        return this.book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmpruntPK that = (EmpruntPK) o;
        return Objects.equals(client_id, that.client_id) &&
                Objects.equals(book_id, that.book_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id, book_id);
    }

}
