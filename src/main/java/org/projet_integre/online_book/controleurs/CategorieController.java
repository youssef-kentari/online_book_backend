package org.projet_integre.online_book.controleurs;

import org.projet_integre.online_book.models.Categorie;
import org.projet_integre.online_book.services.CategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    // CREATE
    @PostMapping
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return categorieService.addCategorie(categorie);
    }

    // READ ALL
    @GetMapping
    public List<Categorie> getAllCategoies() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{categorie}")
    public List<Categorie> getCategorieByName(@PathVariable String categorie) {
        return categorieService.findbyCategorieName(categorie);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        categorieService.deleteCategory(id);
    }
}

