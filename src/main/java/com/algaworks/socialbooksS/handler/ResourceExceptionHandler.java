package com.algaworks.socialbooksS.handler;

import com.algaworks.socialbooksS.domain.DetalhesErro;
import com.algaworks.socialbooksS.service.exceptions.AutoExistenteException;
import com.algaworks.socialbooksS.service.exceptions.AutorNaoEncontradoException;
import com.algaworks.socialbooksS.service.exceptions.LivroNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlaLivroNãoEncontradoException
            (LivroNaoEncontradoException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo("O erro não pode ser encontrado");
        erro.setMensagemDesencolvedor("http://erros.socialbooks.com/404");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(AutoExistenteException.class)
    public ResponseEntity<DetalhesErro> handlaAutoExistenteException
            (AutoExistenteException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409l);
        erro.setTitulo("Autor ja Existente");
        erro.setMensagemDesencolvedor("http://erros.socialbooks.com/409");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlaAutorNaoEncontradoException
            (AutorNaoEncontradoException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo("Autor não encontrado");
        erro.setMensagemDesencolvedor("http://erros.socialbooks.com/404");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handlaDataIntegrityViolationException
            (DataIntegrityViolationException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400l);
        erro.setTitulo("Requisição invalida");
        erro.setMensagemDesencolvedor("http://erros.socialbooks.com/400");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
