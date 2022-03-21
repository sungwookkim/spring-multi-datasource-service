package com.datasource.repo.core.member.write;

import com.datasource.entity.member.Member;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *     회원 저장, 수정, 삭제 인터페이스
 * </pre>
 */
@Component
public interface MemberWrite {

    /**
     * <pre>
     *     회원 저장
     * </pre>
     *
     * @param member
     */
    void save(Member member);
}
