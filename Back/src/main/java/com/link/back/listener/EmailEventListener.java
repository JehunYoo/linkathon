package com.link.back.listener;

import com.link.back.event.email.EmailRejectedEvent;
import com.link.back.event.email.EmailRequestedEvent;
import com.link.back.event.email.EmailRetriedEvent;
import com.link.back.event.email.EmailSentEvent;
import com.link.back.service.EmailEventEntityService;
import com.link.back.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventListener {

    private final EmailService emailService;
    private final EmailEventEntityService emailEventEntityService;

    @Async("emailTransferThreadPoolTaskExecutor")
    @EventListener(EmailRequestedEvent.class)
    public void handleEmailRequestedEvent(EmailRequestedEvent event) {
        emailService.sendEmail(event.getFromEmail(), event.getToEmail(), event.getTitle(), event.getContent(), false);
    }

    @EventListener(EmailRejectedEvent.class)
    public void handleEmailRejectedEvent(EmailRejectedEvent event) {
        emailEventEntityService.storeEvent(event);
    }

    @EventListener(EmailSentEvent.class)
    public void handleEmailSentEvent(EmailSentEvent event) {
        emailEventEntityService.removeEvent(event);
    }

    @Async("emailRetryThreadPoolTaskExecutor")
    @EventListener(EmailRetriedEvent.class)
    public void handleEmailRetriedEvent(EmailRetriedEvent event) {
        emailService.sendEmail(event.getFromEmail(), event.getToEmail(), event.getTitle(), event.getContent(), false);
    }
}

