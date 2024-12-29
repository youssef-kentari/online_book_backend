package org.projet_integre.online_book.services;

import java.util.List;

import org.projet_integre.online_book.models.Categorie;
import org.projet_integre.online_book.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {
        private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }


    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public List<Categorie> findbyCategorieName(String categorie) {
        return categorieRepository.findByCategorie(categorie);
    }

    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategory(Long id) {
        categorieRepository.deleteById(id);
    }
}
