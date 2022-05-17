package com.datasource.entity.noticeBoard;

import java.time.LocalDateTime;

public class Posts {
    private Long seq;

    private final String memberId;
    private final String text;
    private final LocalDateTime registrationDate;

    public Posts(String memberId, String text) {
        this.memberId = memberId;
        this.text = text;
        this.registrationDate = LocalDateTime.now();
    }

    public Long getSeq() {
        return seq;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
