package org.projet_integre.online_book.controleurs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.projet_integre.online_book.models.users.Administrateur;
import org.projet_integre.online_book.repository.AdminRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminControleur {

    private final AdminRepository adminRepository;

    public AdminControleur(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping
    public List<Administrateur> getAllAdmins() {
        return adminRepository.findAll();
    }

    @DeleteMapping("/{tocken}")
    public void delete(@PathVariable Long tocken) {
        adminRepository.deleteByTocken(tocken);
    }

}
