package org.projet_integre.online_book.controleurs;

import org.projet_integre.online_book.DTO.Admin_DTO;
import org.projet_integre.online_book.DTO.Client_DTO;
import org.projet_integre.online_book.models.users.Administrateur;
import org.projet_integre.online_book.models.users.Client;
import org.projet_integre.online_book.repository.AdminRepository;
import org.projet_integre.online_book.repository.ClientRepository;
import org.projet_integre.online_book.services.AdminService;
import org.projet_integre.online_book.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControleur {

    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    public AuthControleur(ClientRepository clientRepository, AdminRepository adminRepository, ClientService clientService, PasswordEncoder passwordEncoder, AdminService adminService) {
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
        this.adminService = adminService;
    }

    @PostMapping("/register/client")
    public ResponseEntity<String> register(@RequestBody Client client){
        client.setPassword(clientService.hashPassword(client.getPassword()));
        clientRepository.save(client);
        return ResponseEntity.ok("Client registered successfully.");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> register(@RequestBody Administrateur admin){
        admin.setPassword(adminService.hashPassword(admin.getPassword()));
        adminRepository.save(admin);
        return ResponseEntity.ok("Administrateur registered successfully.");
    }

    @PostMapping("/login/admin")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin_DTO adminDto) {
        Administrateur storedAdmin = adminRepository.findByTocken(adminDto.getTocken());
        if (storedAdmin == null || !passwordEncoder.matches(adminDto.getPassword(), storedAdmin.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
        return ResponseEntity.ok("Admin login successful.");
    }

    @PostMapping("/login/client")
    public ResponseEntity<String> loginClient(@RequestBody Client_DTO clientDto) {
        Client storedClient = clientRepository.findByMatricule(clientDto.getMatricule());
        if (storedClient == null || !passwordEncoder.matches(clientDto.getPassword(), storedClient.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
        return ResponseEntity.ok("Client login successful.");
    }

}
