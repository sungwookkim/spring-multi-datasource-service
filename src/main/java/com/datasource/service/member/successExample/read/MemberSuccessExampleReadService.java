package com.datasource.service.member.successExample.read;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.read.MemberRead;
import com.datasource.service.member.MemberReadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 *     {@link MemberReadService} 구현체
 * </pre>
 */
@Service
public class MemberSuccessExampleReadService implements MemberReadService {
    private final MemberRead memberReadMapper;

    public MemberSuccessExampleReadService(MemberRead memberReadMapper) {
        this.memberReadMapper = memberReadMapper;
    }

    /**
     * {@link MemberReadService#findId(String)}
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Member findId(String id) {
        return this.memberReadMapper.findId(id);
    }
}
