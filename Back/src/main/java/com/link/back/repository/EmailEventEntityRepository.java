package com.link.back.repository;

import com.link.back.entity.EmailEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailEventEntityRepository extends JpaRepository<EmailEventEntity, Long> {

    @Query("select e from EmailEventEntity e order by e.failedAt asc")
    List<EmailEventEntity> findAllOrderByFailedAt();
}