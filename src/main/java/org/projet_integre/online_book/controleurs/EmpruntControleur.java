package org.projet_integre.online_book.controleurs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.projet_integre.online_book.models.EmpruntPK;
import org.projet_integre.online_book.models.Emprunter;
import org.projet_integre.online_book.services.EmprunterService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/emprunts")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpruntControleur {

    private final EmprunterService emprunterService;

    public EmpruntControleur(EmprunterService emprunterService) {
        this.emprunterService = emprunterService;
    }

    @GetMapping
    public List<Emprunter> getAllEmprunt() {
        return emprunterService.getAllEmprunt();
    }

    @GetMapping("/client/{id}")
    public List<Emprunter> getEmpruntsByClient(@PathVariable Long id) {
        return emprunterService.getEmpruntsByClientId(id);
    }

    @PostMapping
    public boolean addEmprunt(@RequestBody Emprunter emprunt) {
        return emprunterService.addEmprunt(emprunt);
    }

    @PutMapping("/confirmer")
    public void confirmerEmprunt(@RequestParam Long clientId, @RequestParam String isbn) {
        EmpruntPK id = new EmpruntPK(clientId, isbn);
        emprunterService.confirmerEmprunt(id);
    }

    @PutMapping("/annuler")
    public void annulerEmprunt(@RequestParam Long clientId, @RequestParam String isbn) {
        EmpruntPK id = new EmpruntPK(clientId, isbn);
        emprunterService.annulerEmprunt(id);
    }

}
