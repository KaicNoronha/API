package com.algaworks.socialbooksS.service;

import com.algaworks.socialbooksS.domain.Comentario;
import com.algaworks.socialbooksS.domain.Livro;
import com.algaworks.socialbooksS.repository.ComentariosRepository;
import com.algaworks.socialbooksS.repository.LivrosRepository;
import com.algaworks.socialbooksS.service.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LivrosService {


    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private ComentariosRepository comentariosRepository;

    public List<Livro> listar(){
        return livrosRepository.findAll();
    }

    public Livro buscar(Long id){
        Livro livro = livrosRepository.findOne(id);
         if (livro ==null){
            throw new LivroNaoEncontradoException("O livro não pode ser encontrado .");
         }
            return livro;
    }

    public Livro salvar(Livro livro){
        livro.setId(null);
        return livrosRepository.save(livro);

    }

    public  void deletar(Long id){
        try {
        livrosRepository.delete(id);

        }catch (EmptyResultDataAccessException e){
            throw new LivroNaoEncontradoException("Livro não pode ser encontrado");
        }
    }
    public void atualiazar(Livro livro){
        verificarExistencia(livro);
            livrosRepository.save(livro);
    }

    private void  verificarExistencia(Livro livro){
        buscar(livro.getId());
    }

    public  Comentario salvarComentario(Long livroId, Comentario comentario){
        Livro livro = buscar(livroId);
        comentario.setLivro(livro);
        comentario.setData(new Date());

        return comentariosRepository.save(comentario);

    }

    public  List<Comentario> listarComentario(Long livorId){
        Livro livro = buscar(livorId);

        return livro.getComentarios();
    }

}
