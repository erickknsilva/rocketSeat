package com.erickWck.modules.company.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record AuthCompanyDto(
        String username,
        String password
) {
}
