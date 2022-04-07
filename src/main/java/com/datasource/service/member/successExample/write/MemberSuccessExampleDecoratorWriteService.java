package com.datasource.service.member.successExample.write;

import com.datasource.repo.core.member.write.MemberWrite;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.MemberWriteService;
import com.datasource.service.member.successExample.write.abs.MemberSuccessExampleWriteServiceAbs;
import org.springframework.stereotype.Service;

/**
 * {@link MemberWriteService} 추상 클래스
 *
 * 정상적으로 읽기는 slave로 저장/수정/삭제는 master 트랜잭션을 사용하는 예제 클래스
 * 해당 구현체는 Decorator 패턴 예제를 위한 용도
 */
@Service
public class MemberSuccessExampleDecoratorWriteService extends MemberSuccessExampleWriteServiceAbs implements MemberWriteService {

    public MemberSuccessExampleDecoratorWriteService(MemberWrite memberWriteMapper, MemberReadService memberSuccessExampleDecoratorService) {
        super(memberWriteMapper, memberSuccessExampleDecoratorService);
    }
}

