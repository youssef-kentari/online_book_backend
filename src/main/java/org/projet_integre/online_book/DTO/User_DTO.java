package org.projet_integre.online_book.DTO;

public class User_DTO {
    private String email;
    private String password;

    public User_DTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User_DTO() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}