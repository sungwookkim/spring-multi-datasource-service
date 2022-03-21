package com.datasource.service.member;

import com.datasource.entity.member.Member;

/**
 * <pre>
 *     회원 관련 읽기 인터페이스
 * </pre>
 */
public interface MemberReadService {

    /**
     * <pre>
     *     회원 ID로 회원 검색하는 메서드
     * </pre>
     *
     * @param id 회원 정보를 조회하고자 하는 회원 ID
     * @return id로 조회된 회원 정보
     */
    Member findId(String id);
}
