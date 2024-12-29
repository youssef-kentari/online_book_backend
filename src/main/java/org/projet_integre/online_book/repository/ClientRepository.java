package org.projet_integre.online_book.repository;

import org.projet_integre.online_book.models.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByMatricule(Long matricule);

    void deleteByMatricule(Long matricule);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.email = :email, c.password = :password WHERE c.matricule = :matricule")
    void updateClientByMatricule(@Param("matricule") Long matricule, @Param("password") String password,
            @Param("email") String email);
    

}
