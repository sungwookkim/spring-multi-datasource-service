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
 *      추상 클래스의 기본 트랜잭션은 slave이며 master 트랜잭션이 필요한 경우 해당 메서드를 Override하여 master 트랜잭션을 사용하게끔 변경한다.
 * </pre>
 */
public abstract class MemberSuccessExampleReadServiceAbs implements MemberReadService {
    private final MemberRead memberReadMapper;

    public MemberSuccessExampleReadServiceAbs(MemberRead memberReadMapper) {
        this.memberReadMapper = memberReadMapper;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Member findId(String id) {
        return this.memberReadMapper.findId(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Member findName(String name) {
        return this.memberReadMapper.findName(name);
    }
}
