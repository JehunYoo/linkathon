package com.link.back.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class EmailEventEntity {

    @Id
    @GeneratedValue
    private Long emailEventEntityId;

    private String fromEmail;
    private String toEmail;
    private String title;
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime failedAt;

    @Builder
    public EmailEventEntity(String fromEmail, String toEmail, String title, String content) {
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.title = title;
        this.content = content;
    }
}

