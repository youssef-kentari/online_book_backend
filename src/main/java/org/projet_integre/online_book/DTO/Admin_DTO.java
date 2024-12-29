package org.projet_integre.online_book.DTO;

public class Admin_DTO {
    private Long tocken;
    private String password;

    public Admin_DTO(Long tocken, String password) {
        this.tocken = tocken;
        this.password = password;
    }

    public Long getTocken() {
        return this.tocken;
    }

    public void setTocken(Long tocken) {
        this.tocken = tocken;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
