package org.projet_integre.online_book.repository;

import org.projet_integre.online_book.models.users.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AdminRepository extends JpaRepository<Bibliothecaire, Long>{
    @Query("SELECT b FROM Bibliothecaire b WHERE b.tocken=:tocken")
    Bibliothecaire findByTocken(Long tocken);
    void deleteByTocken(Long tocken);

}
