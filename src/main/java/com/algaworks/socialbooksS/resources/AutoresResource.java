package com.algaworks.socialbooksS.resources;

import com.algaworks.socialbooksS.domain.Autor;
import com.algaworks.socialbooksS.service.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutoresResource {

    @Autowired
    private AutoresService autoresService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Autor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salva(@Valid @RequestBody Autor autor) {
        autor = autoresService.salvar(autor);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(autor.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Autor> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.buscar(id));

    }

}
