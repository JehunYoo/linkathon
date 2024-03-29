package com.link.back.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequest {

    @NotEmpty
    private String email;
    private String password;
    private String name;
    private String nickname;
    private LocalDate birth;
}

