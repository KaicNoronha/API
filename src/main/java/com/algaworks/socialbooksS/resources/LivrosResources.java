package com.algaworks.socialbooksS.resources;

import com.algaworks.socialbooksS.domain.Comentario;
import com.algaworks.socialbooksS.domain.Livro;
import com.algaworks.socialbooksS.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosService livrosService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> litar(){
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro){
       livro = livrosService.salvar(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Livro livro =  livrosService.buscar(id);;
          return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<Void> deletar(@PathVariable("id")Long id){
        livrosService.deletar(id);
         return ResponseEntity.noContent().build();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
        livro.setId(id);
        livrosService.atualiazar(livro);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}/comentarios",method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroid, @RequestBody Comentario comentario){

        livrosService.salvarComentario(livroid, comentario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<List<Comentario>> listarComentario(@PathVariable("id") Long livroid){
        List<Comentario> comentarios = livrosService.listarComentario(livroid);

        return ResponseEntity.status(HttpStatus.OK).body(comentarios);
    }

}
