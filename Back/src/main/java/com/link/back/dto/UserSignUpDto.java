package com.link.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignUpDto {

    private String email;
    private String password;
    private String name;
    private boolean gender;
    private LocalDate birth;
    private String phoneNumber;
    private int career;

}
