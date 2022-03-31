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
 *     Master용 Read로 정의한 클래스이기 때문에 추상 클래스에 선언된 slave 트랜잭션 메서드 중 사용하지 않는 메서드들을 예외를 던진다.
 *     추후 필요한 경우에 해당 메서드의 Transactional을 master로 Override 한다.
 * </pre>
 */
@Service
public class MemberSuccessExampleMasterReadService extends MemberSuccessExampleReadServiceAbs implements MemberReadService {

    public MemberSuccessExampleMasterReadService(MemberRead memberReadMapper) {
        super(memberReadMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Member findId(String id) {
        return super.findId(id);
    }

    @Override
    public Member findName(String name) {
        throw new IllegalStateException("지원되지 않는 메서드 입니다.");
    }
}
