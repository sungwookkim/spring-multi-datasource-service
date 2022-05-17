package com.datasource.service.member.successExample.read;

import com.datasource.entity.member.Member;
import com.datasource.service.member.MemberReadService;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * <pre>
 *     {@link MemberReadService} 구현체
 *
 *     읽기 Master/Slave 데코레이터 패턴
 * </pre>
 */
@Component
public class MemberSuccessExampleDecoratorService implements MemberReadService {
    private final MemberSuccessExampleFactory memberSuccessExampleFactory;

    public MemberSuccessExampleDecoratorService(MemberSuccessExampleFactory memberSuccessExampleFactory) {
        this.memberSuccessExampleFactory = memberSuccessExampleFactory;
    }

    /**
     * {@link MemberReadService#findId(String)}
     */
    @Override
    public Member findId(String id) {
        return Optional.ofNullable(this.memberSuccessExampleFactory.getInstance(MemberSuccessExampleSlaveReadService.class)
                    .findId(id))
            .orElseGet(() -> this.memberSuccessExampleFactory.getInstance(MemberSuccessExampleMasterReadService.class)
                    .findId(id));
    }

    /**
     * {@link MemberReadService#findName(String)}
     */
    @Override
    public Member findName(String name) {
        return Optional.ofNullable(this.memberSuccessExampleFactory.getInstance(MemberSuccessExampleSlaveReadService.class)
                    .findName(name))
                .orElseGet(() -> this.memberSuccessExampleFactory.getInstance(MemberSuccessExampleMasterReadService.class)
                    .findName(name));
    }
}
