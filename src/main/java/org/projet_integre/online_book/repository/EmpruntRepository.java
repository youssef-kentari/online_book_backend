package org.projet_integre.online_book.repository;

import java.util.List;

import org.projet_integre.online_book.models.Emprunter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface EmpruntRepository extends JpaRepository<Emprunter, Long> {

    @Query("SELECT e FROM Emprunter e WHERE e.client.id =:id AND e.etat = 'ENCOURS'")
    List<Emprunter> findEmpruntsByClientId(Long id);

    @Query("SELECT e FROM Emprunter e WHERE e.client.id =:id AND e.etat = 'ENCOURS' AND e.livre.isbn =:isbn")
    List<Emprunter> findEmpruntsByClientAndBook(Long id, String isbn);

    @Transactional
    @Modifying
    @Query("UPDATE Emprunter e SET e.etat='TERMINER' WHERE e.id =:id")
    void annulerEmprunt(@Param("id") Long id);
}
