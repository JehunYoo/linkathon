package com.link.back.event.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class EmailRequestedEvent implements EmailEvent {

    private final Object source;
    private final Long emailEventId;
    private final String fromEmail;
    private final String toEmail;
    private final String title;
    private final String content;

    /**
     * Create EmailRequestedEvent from database with emailEventId.
     */
    public static EmailRequestedEvent of(Object source, Long emailEventId, String fromEmail, String toEmail, String title, String content) {
        if (emailEventId == null) {
            throw new IllegalArgumentException();
        }

        return new EmailRequestedEvent(source, emailEventId, fromEmail, toEmail, title, content);
    }

    /**
     * Create EmailRequestedEvent directly from API request without emailEventId.
     */
    public static EmailRequestedEvent of(Object source, String fromEmail, String toEmail, String title, String content) {
        return new EmailRequestedEvent(source, null, fromEmail, toEmail, title, content);
    }

    public boolean isStored() {
        return this.emailEventId != null;
    }
}
