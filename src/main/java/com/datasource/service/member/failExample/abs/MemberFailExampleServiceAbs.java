package com.datasource.service.member.failExample.abs;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.read.MemberRead;
import com.datasource.repo.core.member.write.MemberWrite;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.MemberWriteService;
import com.datasource.service.member.failExample.MemberFailExampleService;

import java.util.Optional;

/**
 * {@link MemberFailExampleService} 추상 클래스
 *
 * 실패 예제에 사용되는 행위들을 구현.
 * 실제 구현체에서는 트랜잭션 어노테이션만 활용하기 위해 생성
 */
public abstract class MemberFailExampleServiceAbs implements MemberFailExampleService {
    private final MemberWrite memberWriteMapper;
    private final MemberRead memberReadMapper;

    public MemberFailExampleServiceAbs(MemberWrite memberWriteMapper, MemberRead memberReadMapper) {
        this.memberWriteMapper = memberWriteMapper;
        this.memberReadMapper = memberReadMapper;
    }

    /**
     * {@link MemberWriteService#save(Member)}
     */
    @Override
    public void save(Member member) {
        // 해당 메서드 호출 시 정상적인 설정이라면 slave 트랜잭션이 발생 되어야 한다.
        Member findMember = Optional.ofNullable(this.findId(member.getId()))
                .orElseGet(Member::defaultObj);

        Optional.ofNullable(findMember.getId())
                .filter(""::equals)
                .orElseThrow(() -> new IllegalStateException("존재하는 회원 ID 입니다."));

        this.memberWriteMapper.save(member);
    }

    /**
     * {@link MemberReadService#findId(String)}
     */
    @Override
    public Member findId(String id) {
        return this.memberReadMapper.findId(id);
    }

    /**
     * {@link MemberReadService#findName(String)}
     */
    @Override
    public Member findName(String name) {
        return this.memberReadMapper.findName(name);
    }
}