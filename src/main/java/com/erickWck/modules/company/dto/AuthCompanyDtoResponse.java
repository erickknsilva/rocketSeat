package com.erickWck.modules.company.dto;

import lombok.Builder;

@Builder
public record AuthCompanyDtoResponse(

        String acess_token,
        Long expires_in
) {

}
