package com.datasource.service.member.successExample.write.abs;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.write.MemberWrite;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.MemberWriteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <pre>
 *     {@link MemberWriteService} 추상클래스
 *     Write 메소드에서 Read 시 상황에 따라 Master/SLave 트랜잭션을 활용하기 위한 예제 추상클래스
 * </pre>
 */
public abstract class MemberSuccessExampleWriteServiceAbs implements MemberWriteService {
    private final MemberWrite memberWriteMapper;
    private final MemberReadService memberReadService;

    public MemberSuccessExampleWriteServiceAbs(MemberWrite memberWriteMapper, MemberReadService memberReadService) {
        this.memberWriteMapper = memberWriteMapper;
        this.memberReadService = memberReadService;
    }

    /**
     * {@link MemberWriteService#save(Member)}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void save(Member member) {
        // 해당 메서드 호출 시 MemberReadService 구현체에 따라 Master 혹은 Slave 트랜잭션을 사용하여야 한다.
        Member findMember = Optional.ofNullable(this.memberReadService.findId(member.getId()))
                .orElseGet(Member::defaultObj);

        Optional.ofNullable(findMember.getId())
                .filter(""::equals)
                .orElseThrow(() -> new IllegalStateException("존재하는 회원 ID 입니다."));

        this.memberWriteMapper.save(member);
    }
}
