package com.link.back.event.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class EmailRetriedEvent implements EmailEvent {

    private final Object source;
    private final Long emailEventId;
    private final String fromEmail;
    private final String toEmail;
    private final String title;
    private final String content;
}
