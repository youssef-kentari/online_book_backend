package org.projet_integre.online_book.repository;

import org.projet_integre.online_book.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User,Long>{
    @Query("SELECT u FROM User u WHERE u.username =:username")
    User findByUsername(String username);
}
