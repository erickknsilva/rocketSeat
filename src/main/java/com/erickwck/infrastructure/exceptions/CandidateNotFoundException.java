package com.erickwck.infrastructure.exceptions;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException() {
        super("Candidate not found");
    }
}
