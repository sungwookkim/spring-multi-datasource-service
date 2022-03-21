package com.datasource.service.member.failExample;

import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.MemberWriteService;

/**
 * {@link MemberReadService}, {@link MemberWriteService} 인터페이스
 * 읽기/수정/삭제가 같은 구현체 내에 있는 경우 실패 예를 들기 위한 인터페이스
 */
public interface MemberFailExampleService extends MemberReadService, MemberWriteService {
}
