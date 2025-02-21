package com.erickWck.infra.exceptions;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("Job not found");
    }
}
