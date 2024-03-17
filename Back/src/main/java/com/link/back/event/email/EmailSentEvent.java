package com.link.back.event.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class EmailSentEvent {

    private final Long emailEventId;
}
