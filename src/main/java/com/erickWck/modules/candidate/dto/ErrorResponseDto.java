package com.erickWck.modules.candidate.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public record ErrorResponseDto(
        String message,
        HttpStatus status,
        Integer statusCode
) {

    @Builder(toBuilder = true)
    public ErrorResponseDto {
    }


}