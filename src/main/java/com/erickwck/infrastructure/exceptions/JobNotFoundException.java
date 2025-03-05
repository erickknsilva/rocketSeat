package com.erickwck.infrastructure.exceptions;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("Job not found");
    }
}
