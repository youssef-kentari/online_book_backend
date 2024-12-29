package org.projet_integre.online_book.controleurs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.projet_integre.online_book.models.Emprunter;
import org.projet_integre.online_book.repository.EmpruntRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/emprunts")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpruntControleur {

    private final EmpruntRepository empruntRepository;

    public EmpruntControleur(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    @GetMapping
    public List<Emprunter> getAllEmprunt() {
        return empruntRepository.findAll();
    }

    @PostMapping
    public Emprunter addEmprunt(@RequestBody Emprunter emprunt) {
        return empruntRepository.save(emprunt);
    }

    /*@DeleteMapping("/delete")
    public void deleteEmprunt(@RequestParam Long clientId, @RequestParam String livreId) {
        empruntRepository.deleteByClientIdAndLivreId(clientId, livreId);
    }*/

}
