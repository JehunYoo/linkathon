package com.link.back.event.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class EmailRejectedEvent implements EmailEvent {

    private final Object source;
    private final String fromEmail;
    private final String toEmail;
    private final String title;
    private final String content;
}
