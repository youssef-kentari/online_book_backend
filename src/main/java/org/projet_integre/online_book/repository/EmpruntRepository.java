package org.projet_integre.online_book.repository;

import java.util.List;

import org.projet_integre.online_book.models.ClientBookPK;
import org.projet_integre.online_book.models.Emprunter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpruntRepository extends JpaRepository<Emprunter, ClientBookPK> {


}
