package org.projet_integre.online_book.repository;

import java.util.List;

import org.projet_integre.online_book.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findByCategorie(String categorie);
}
