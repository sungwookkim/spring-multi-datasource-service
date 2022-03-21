package com.datasource.repo.core.member.read;

import com.datasource.entity.member.Member;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *     회원 조회 인터페이스
 * </pre>
 */
@Component
public interface MemberRead {

    /**
     * <pre>
     *     회원 ID 조건으로 회원 검색
     * </pre>
     *
     * @param id
     * @return
     */
    Member findId(String id);

    /**
     * <pre>
     *     회원 name 조건으로 회원 검색
     * </pre>
     *
     * @param name
     * @return
     */
    Member findName(String name);
}
