package org.projet_integre.online_book.repository;

import org.projet_integre.online_book.models.users.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Administrateur, Long>{
    Administrateur findByTocken(Long tocken);
    void deleteByTocken(Long tocken);

}
