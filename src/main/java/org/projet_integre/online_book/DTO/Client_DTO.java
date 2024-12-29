package org.projet_integre.online_book.DTO;

public class Client_DTO {
    private Long matricule;
    private String password;


    public Client_DTO(Long matricule, String password) {
        this.matricule = matricule;
        this.password = password;
    }

    public Long getMatricule() {
        return this.matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}