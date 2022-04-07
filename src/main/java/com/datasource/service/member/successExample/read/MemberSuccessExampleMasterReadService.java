package com.datasource.service.member.successExample.read;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.read.MemberRead;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.successExample.read.abs.MemberSuccessExampleReadServiceAbs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 *     {@link MemberReadService} 구현체
 *
 *     Master용 Read로 정의한 클래스이기 때문에 추상 클래스에 선언된 slave 트랜잭션 메서드를 Master 트랜잭션으로 재정의한다.
 * </pre>
 */
@Service
public class MemberSuccessExampleMasterReadService extends MemberSuccessExampleReadServiceAbs implements MemberReadService {

    public MemberSuccessExampleMasterReadService(MemberRead memberReadMapper) {
        super(memberReadMapper);
    }

    /**
     * {@link MemberReadService#findId(String)}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Member findId(String id) {
        return super.findId(id);
    }

    /**
     * {@link MemberReadService#findName(String)}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Member findName(String name) {
        return super.findName(name);
    }
}
