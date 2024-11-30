package com.erickWck.infra.exceptions;

public class UsuarioAlreadExist extends RuntimeException {

    public UsuarioAlreadExist() {
        super("Usuario já cadastrado no sistema.");
    }

}