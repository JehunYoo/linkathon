package com.link.back.repository;

import org.springframework.data.repository.CrudRepository;

import com.link.back.dto.VerificationCode;

public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String>{

}