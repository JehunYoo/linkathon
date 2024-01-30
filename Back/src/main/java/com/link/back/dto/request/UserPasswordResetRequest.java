package com.link.back.dto.request;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class UserPasswordResetRequest {

    private String email;
    private String password;
    private String verificationKey;

}
