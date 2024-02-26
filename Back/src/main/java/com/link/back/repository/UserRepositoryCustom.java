package com.link.back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.link.back.dto.request.UserSearchConditionDto;
import com.link.back.entity.User;

public interface UserRepositoryCustom {
	Page<User> findBySearchCondition(Pageable pageable, UserSearchConditionDto userSearchConditionDto);

}
