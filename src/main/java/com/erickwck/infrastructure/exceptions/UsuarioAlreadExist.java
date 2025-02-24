package com.erickwck.infrastructure.exceptions;

public class UsuarioAlreadExist extends RuntimeException {

    public UsuarioAlreadExist() {
        super("Usuario já cadastrado no sistema.");
    }

}