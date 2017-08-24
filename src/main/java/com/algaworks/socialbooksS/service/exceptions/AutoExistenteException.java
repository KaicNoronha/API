package com.algaworks.socialbooksS.service.exceptions;


public class AutoExistenteException extends  RuntimeException{

    private  static final  long serialVersionUID = 1869300553614629710L;

    public AutoExistenteException(String mensagem){
        super(mensagem);
    }

    public AutoExistenteException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
