package org.projet_integre.online_book.services;

import java.util.List;
import java.util.Optional;

import org.projet_integre.online_book.models.Book;
import org.projet_integre.online_book.models.EmpruntPK;
import org.projet_integre.online_book.models.Emprunter;
import org.projet_integre.online_book.models.Etat;
import org.projet_integre.online_book.repository.BookRepository;
import org.projet_integre.online_book.repository.EmpruntRepository;
import org.springframework.stereotype.Service;


@Service
public class EmprunterService {
    
    private final EmpruntRepository empruntRepository;
    private BookService bookService;
    private BookRepository bookRepository;

    public EmprunterService(
        EmpruntRepository empruntRepository,
        BookService bookService,
        BookRepository bookRepository) {

        this.bookService = bookService;
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
        List<Emprunter> empruntsByClientAndBook = empruntRepository.findEmpruntsByClientAndBook(emprunt.getId().getClient_id(),emprunt.getId().getBook_id());
        System.out.println(empruntsByClientAndBook);
        if (empruntsByClientAndBook.size()!=0) {
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
                book.setNbrExemplaireAvai(book.getNbrExemplaireAvai()-1);
                bookService.updateBook(emprunt.getLivre().getIsbn(), book);
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

    public void confirmerEmprunt(EmpruntPK id){
        empruntRepository.confirmerEmprunt(id);
    }


    public void annulerEmprunt(EmpruntPK id){
        Optional<Emprunter> empruntOptional = empruntRepository.findById(id);
        Emprunter emprunt = empruntOptional.get();
        Optional<Book> bookOptional = bookRepository.findById(emprunt.getLivre().getIsbn());
        Book book = bookOptional.get();
        book.setNbrExemplaireAvai(book.getNbrExemplaireAvai()+1);
        bookService.updateBook(emprunt.getLivre().getIsbn(), book);
        empruntRepository.annulerEmprunt(id);
    }
}
