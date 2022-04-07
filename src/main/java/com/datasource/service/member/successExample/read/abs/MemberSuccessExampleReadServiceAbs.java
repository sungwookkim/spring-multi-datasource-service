package com.datasource.service.member.successExample.read.abs;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.read.MemberRead;
import com.datasource.service.member.MemberReadService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 *      조회 트랜잭션을 master/slave로 구분짓기 위한 추상 클래스.
 *
 *      추상 클래스의 기본 트랜잭션은 slave 트랜잭션을 사용한다.
 * </pre>
 */
public abstract class MemberSuccessExampleReadServiceAbs implements MemberReadService {
    private final MemberRead memberReadMapper;

    public MemberSuccessExampleReadServiceAbs(MemberRead memberReadMapper) {
        this.memberReadMapper = memberReadMapper;
    }

    /**
     * {@link MemberReadService#findId(String)}
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Member findId(String id) {
        return this.memberReadMapper.findId(id);
    }

    /**
     * {@link MemberReadService#findName(String)}
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Member findName(String name) {
        return this.memberReadMapper.findName(name);
    }
}
