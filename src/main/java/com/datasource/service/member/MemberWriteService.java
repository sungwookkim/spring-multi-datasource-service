package com.datasource.service.member;

import com.datasource.entity.member.Member;

/**
 * <pre>
 *     회원 관련 저장/수정/삭제 인터페이스
 * </pre>
 */
public interface MemberWriteService {

    /**
     * <pre>
     *     예시 회원 가입 - 정상적인 트랜잭션
     *     읽기 시 정상적으로 slave를 사용.
     * </pre>
     *
     * @param member 회원 등록을 위한 회원 정보
     */
    void save(Member member);
}
