package com.datasource.service.member.successExample.read;

import com.datasource.service.member.MemberReadService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 *     Read 팩토리
 * </pre>
 */
@Component
public class MemberSuccessExampleFactory {
    private final Map<String, MemberReadService> memberReadServiceMap = new HashMap<>();

    public MemberSuccessExampleFactory(MemberReadService memberSuccessExampleSlaveReadService
            , MemberReadService memberSuccessExampleMasterReadService) {
        // Master 트랜잭션 용 Read
        this.memberReadServiceMap.put(MemberSuccessExampleMasterReadService.class.getSimpleName()
                , memberSuccessExampleMasterReadService);

        // Slave 트랜잭션 용 Read
        this.memberReadServiceMap.put(MemberSuccessExampleSlaveReadService.class.getSimpleName()
                , memberSuccessExampleSlaveReadService);
    }

    /**
     * <pre>
     *     Read 트랜잭션 객체 반환.
     * </pre>
     * @param clazz
     * @return
     */
    public MemberReadService getInstance(Class<? extends MemberReadService> clazz) {
        return Optional.ofNullable(this.memberReadServiceMap.get(clazz.getSimpleName()))
                .orElseThrow(() -> new IllegalStateException("지원되지 않는 객체 입니다."));
    }
}
