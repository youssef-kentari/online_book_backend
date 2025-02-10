package org.projet_integre.online_book.repository;

import org.projet_integre.online_book.models.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Long> {

    Optional<OurUsers> findByEmail(String email);
}
