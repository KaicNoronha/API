package com.algaworks.socialbooksS.service;

import com.algaworks.socialbooksS.domain.Autor;
import com.algaworks.socialbooksS.repository.AutoresRepository;
import com.algaworks.socialbooksS.service.exceptions.AutoExistenteException;
import com.algaworks.socialbooksS.service.exceptions.AutorNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoresService {

    private AutoresRepository autoresRepository;

    public List<Autor> listar() {
        return autoresRepository.findAll();
    }

    public Autor salvar(Autor autor) {
        if (autor.getId() != null) {
            Autor a = autoresRepository.findOne(autor.getId());

            if(a != null) {
                throw  new AutoExistenteException("O autor ja existe");
            }
        }
        return autoresRepository.save(autor);
    }

    public Autor buscar (Long id){

            Autor autor = autoresRepository.findOne(id);
        if (autor == null){
            throw new AutorNaoEncontradoException("Autor não encontrado");
        }
        return autor;
    }

}