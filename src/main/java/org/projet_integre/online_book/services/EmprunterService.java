package org.projet_integre.online_book.services;

import java.util.List;
import java.util.Optional;

import org.projet_integre.online_book.models.Book;
import org.projet_integre.online_book.models.Emprunter;
import org.projet_integre.online_book.models.Etat;
import org.projet_integre.online_book.repository.BookRepository;
import org.projet_integre.online_book.repository.EmpruntRepository;
import org.springframework.stereotype.Service;


@Service
public class EmprunterService {
    
    private final EmpruntRepository empruntRepository;
    private BookRepository bookRepository;

    public EmprunterService(
        EmpruntRepository empruntRepository,
        BookRepository bookRepository) {

        this.empruntRepository = empruntRepository;
        this.bookRepository=bookRepository;
    }

    public List<Emprunter> getAllEmprunt() {
        return empruntRepository.findAll();
    }

    public List<Emprunter> getEmpruntsByClientId(Long id) {
        return empruntRepository.findEmpruntsByClientId(id);
    }

    public boolean addEmprunt(Emprunter emprunt) {
        // Vérifier si le client a déjà emprunté plus de 5 livres
        List<Emprunter> empruntsByClient = empruntRepository.findEmpruntsByClientId(emprunt.getClient().getId());
        if (empruntsByClient.size() > 5) {
            // Log ou lever une exception personnalisée
            System.out.println("Le client a atteint la limite d'emprunts.");
            return false;
        }

        // Vérifier si le client a déjà emprunté le même livre
        List<Emprunter> empruntsByClientAndBook = empruntRepository.findEmpruntsByClientAndBook(emprunt.getClient().getId(), emprunt.getLivre().getIsbn());
        if (!empruntsByClientAndBook.isEmpty()) {
            // Log ou lever une exception personnalisée
            System.out.println("Le client a déjà emprunté ce livre.");
            return false;
        }

        // Vérifier si le livre existe et s'il reste des exemplaires disponibles
        Optional<Book> bookOptional = bookRepository.findById(emprunt.getLivre().getIsbn());
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (book.getNbrExemplaireAvai() > 0) {

                // Enregistrer l'emprunt avec l'état "PENDING"
                emprunt.setEtat(Etat.PENDING);
                empruntRepository.save(emprunt);
                return true;
            } else {
                // Log ou lever une exception personnalisée
                System.out.println("Aucun exemplaire disponible pour ce livre.");
                return false;
            }
        }

        // Log ou lever une exception personnalisée pour indiquer que le livre n'existe pas
        System.out.println("Livre non trouvé.");
        return false;
    }


    public void annulerEmprunt(Long id){
        Optional<Emprunter> empruntOptional = empruntRepository.findById(id);
        Emprunter emprunt = empruntOptional.get();
        emprunt.getLivre().setNbrExemplaireAvai(emprunt.getLivre().getNbrExemplaireAvai()-1);
        empruntRepository.annulerEmprunt(id);
    }
}
