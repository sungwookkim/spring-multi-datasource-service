package com.datasource.service.member.failExample;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.read.MemberRead;
import com.datasource.repo.core.member.write.MemberWrite;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.MemberWriteService;
import com.datasource.service.member.failExample.abs.MemberFailExampleServiceAbs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * {@link MemberFailExampleService} 구현체
 *
 * 정상적으로 읽기는 slave로 저장/수정/삭제는 master 트랜잭션을 사용해야 하나 master만 사용하는 실패 예제
 * 트랜잭션 속성 중 readOnly를 true 사용.
 */
@Service
public class MemberReadOnlyFailExampleService extends MemberFailExampleServiceAbs implements MemberFailExampleService {
    public MemberReadOnlyFailExampleService(MemberWrite memberWriteMapper, MemberRead memberReadMapper) {
        super(memberWriteMapper, memberReadMapper);
    }

    /**
     * {@link MemberFailExampleServiceAbs#save(Member)}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void save(Member member) {
        super.save(member);
    }

    /**
     * {@link MemberFailExampleServiceAbs#findId(String)}
     */
    @Override
    @Transactional(readOnly = true)
    public Member findId(String id) {
        return super.findId(id);
    }
}
