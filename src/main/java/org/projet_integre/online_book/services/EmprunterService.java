package org.projet_integre.online_book.services;

import java.util.List;

import org.projet_integre.online_book.models.Emprunter;
import org.projet_integre.online_book.models.Etat;
import org.projet_integre.online_book.repository.ClientRepository;
import org.projet_integre.online_book.repository.EmpruntRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmprunterService {
    
    private final EmpruntRepository empruntRepository;
    private final ClientRepository clientRepository;

    public EmprunterService(
        EmpruntRepository empruntRepository,
        ClientRepository clientRepository) {

        this.empruntRepository = empruntRepository;
        this.clientRepository = clientRepository;   
    }

    public List<Emprunter> getAllEmprunt() {
        return empruntRepository.findAll();
    }

    public List<Emprunter> getEmpruntsByClientId(Long id) {
        return empruntRepository.findEmpruntsByClientId(id);
    }

    public boolean addEmprunt(Emprunter emprunt) {
        List<Emprunter> empruntsByClient = empruntRepository.findEmpruntsByClientId(emprunt.getClient().getId());
        List<Emprunter> empruntsByClientAndBook = empruntRepository.findEmpruntsByClientAndBook(emprunt.getClient().getId(),emprunt.getLivre().getIsbn());

        if (empruntsByClient.size() > 5) {
            return false;
        }

        if (empruntsByClientAndBook.size() >= 1) {
            return false;
        }

        emprunt.setEtat(Etat.ENCOURS);
        empruntRepository.save(emprunt);
        return true;
    }

    public void annulerEmprunt(Long id){
        empruntRepository.annulerEmprunt(id);
    }
}
