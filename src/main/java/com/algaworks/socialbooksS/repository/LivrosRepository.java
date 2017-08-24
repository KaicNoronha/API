package com.algaworks.socialbooksS.repository;

import com.algaworks.socialbooksS.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

}
