package com.datasource.entity.member;

import java.time.LocalDateTime;

/**
 * <pre>
 *     회원 Entity
 * </pre>
 */
public class Member {
    private Long memberSeq;

    private final String id;
    private final String name;
    private final Integer age;
    private final LocalDateTime regDtm = LocalDateTime.now();

    public Member(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDateTime getRegDtm() {
        return regDtm;
    }

    public Long getMemberSeq() {
        return memberSeq;
    }

    public String getName() {
        return name;
    }

    public static Member defaultObj() {
        return new Member("", "", 0);
    }
}
