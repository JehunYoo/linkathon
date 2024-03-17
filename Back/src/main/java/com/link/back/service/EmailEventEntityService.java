package com.link.back.service;

import com.link.back.entity.EmailEventEntity;
import com.link.back.event.email.EmailEvent;
import com.link.back.event.email.EmailRetriedEvent;
import com.link.back.event.email.EmailSentEvent;
import com.link.back.repository.EmailEventEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailEventEntityService {

    private final ApplicationEventPublisher eventPublisher;
    private final EmailEventEntityRepository emailEventEntityRepository;

    public void storeEvent(EmailEvent emailEvent) {
        EmailEventEntity emailEventEntity = EmailEventEntity.builder()
                .fromEmail(emailEvent.getFromEmail())
                .toEmail(emailEvent.getToEmail())
                .title(emailEvent.getTitle())
                .content(emailEvent.getContent())
                .build();

        log.info("storing failed event {}", emailEvent.getToEmail());
        emailEventEntityRepository.save(emailEventEntity);
    }

    @Transactional(readOnly = true)
    @Scheduled(cron = "${email.retry.cron}") // todo
    public void retryEvent() {
        List<EmailEventEntity> emailEventEntities = emailEventEntityRepository.findAllOrderByFailedAt();
        log.info("retrying {} emails", emailEventEntities.size());
        emailEventEntities.stream()
                .map(entity -> new EmailRetriedEvent(this, entity.getEmailEventEntityId(), entity.getFromEmail(), entity.getToEmail(), entity.getTitle(), entity.getContent()))
                .forEach(eventPublisher::publishEvent);
    }

    public void removeEvent(EmailSentEvent event) {
        log.info("removing success event {}", event.getEmailEventId());
        emailEventEntityRepository.deleteById(event.getEmailEventId());
    }
}

