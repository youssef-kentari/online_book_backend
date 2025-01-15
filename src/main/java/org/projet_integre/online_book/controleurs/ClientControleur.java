package org.projet_integre.online_book.controleurs;

import java.util.List;

import org.projet_integre.online_book.models.users.Client;
import org.projet_integre.online_book.repository.ClientRepository;
import org.projet_integre.online_book.services.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientControleur {

    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public ClientControleur(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{matricule}")
    public Client getClientByMatricule(@PathVariable Long matricule) {
        return clientRepository.findByMatricule(matricule);
    }

    @PutMapping("/{matricule}")
    public void updateClient(@PathVariable Long matricule, @RequestParam String email, @RequestParam String password){
        clientRepository.updateClientByMatricule(matricule,password,email);
    }

    @DeleteMapping("/{matricule}")
    public void deleteClient(@PathVariable Long matricule){
        clientRepository.deleteByMatricule(matricule);
    }

    
}