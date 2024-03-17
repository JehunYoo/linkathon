package com.link.back.event.email;

public interface EmailEvent {

    String getFromEmail();

    String getToEmail();

    String getTitle();

    String getContent();
}
