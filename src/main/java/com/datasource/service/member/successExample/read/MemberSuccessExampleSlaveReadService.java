package com.datasource.service.member.successExample.read;

import com.datasource.repo.core.member.read.MemberRead;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.successExample.read.abs.MemberSuccessExampleReadServiceAbs;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     {@link MemberReadService} 구현체
 * </pre>
 */
@Service
public class MemberSuccessExampleSlaveReadService extends MemberSuccessExampleReadServiceAbs implements MemberReadService {

    public MemberSuccessExampleSlaveReadService(MemberRead memberReadMapper) {
        super(memberReadMapper);
    }

}
