package com.datasource.entity.noticeBoard.control;

import com.datasource.enums.NoticeBoardTypeEnum;

import java.time.LocalDateTime;

public class NoticeBoardControl {
    private Long seq;

    private final Long referenceSeq;
    private final String noticeBoardType;
    private final LocalDateTime registrationDate;

    public NoticeBoardControl(Long referenceSeq, NoticeBoardTypeEnum noticeBoardTypeEnum) {
        this.referenceSeq = referenceSeq;
        this.noticeBoardType = noticeBoardTypeEnum.getType();
        this.registrationDate = LocalDateTime.now();
    }

    public NoticeBoardControl(Long referenceSeq, String noticeBoardType) {
        this.referenceSeq = referenceSeq;
        this.noticeBoardType = noticeBoardType;
        this.registrationDate = LocalDateTime.now();
    }

    public Long getSeq() {
        return seq;
    }

    public Long getReferenceSeq() {
        return referenceSeq;
    }

    public String getNoticeBoardType() {
        return noticeBoardType;
    }

    public NoticeBoardTypeEnum getNoticeBoardTypeEnum() {
        return NoticeBoardTypeEnum.find(this.noticeBoardType);
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }


}
