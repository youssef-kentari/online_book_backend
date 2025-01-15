package org.projet_integre.online_book.controleurs;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.projet_integre.online_book.models.users.Bibliothecaire;
import org.projet_integre.online_book.repository.AdminRepository;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminControleur {

    private final AdminRepository adminRepository;

    public AdminControleur(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping
    public List<Bibliothecaire> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping
    public Bibliothecaire addAdmin(@RequestBody Bibliothecaire bibliothecaire){
        return adminRepository.save(bibliothecaire);
    }

    @DeleteMapping("/{tocken}")
    public void delete(@PathVariable Long tocken) {
        adminRepository.deleteByTocken(tocken);
    }

}
