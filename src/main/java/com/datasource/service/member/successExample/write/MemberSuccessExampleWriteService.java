package com.datasource.service.member.successExample.write;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.write.MemberWrite;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.MemberWriteService;
import com.datasource.service.member.successExample.read.MemberSuccessExampleReadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * {@link MemberWriteService} 구현체
 * 
 * 정상적으로 읽기는 slave로 저장/수정/삭제는 master 트랜잭션을 사용하는 예제 클래스
 */
@Service
public class MemberSuccessExampleWriteService implements MemberWriteService {
    private final MemberWrite memberWriteMapper;
    private final MemberReadService memberSuccessExampleReadService;

    public MemberSuccessExampleWriteService(MemberWrite memberWriteMapper
            , MemberReadService memberSuccessExampleReadService) {
        this.memberWriteMapper = memberWriteMapper;
        this.memberSuccessExampleReadService = memberSuccessExampleReadService;
    }

    /**
     * {@link MemberWriteService#save(Member)}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void save(Member member) {
        // 해당 메서드 호출 시 정상적인 설정이라면 slave 트랜잭션이 발생 되어야 한다.
        Member findMember = Optional.ofNullable(this.memberSuccessExampleReadService.findId(member.getId()))
                .orElseGet(Member::defaultObj);

        Optional.ofNullable(findMember.getId())
                .filter(""::equals)
                .orElseThrow(() -> new IllegalStateException("존재하는 회원 ID 입니다."));

        this.memberWriteMapper.save(member);
    }
}
