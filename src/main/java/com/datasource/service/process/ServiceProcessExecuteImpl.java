package com.datasource.service.process;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 *     {@link Service} 어노테이션을 선언하는 클래스
 *     {@link org.springframework.transaction.annotation.Transactional} 처리를 위한 목적으로 생성
 * </pre>
 */
@Service
public class ServiceProcessExecuteImpl {

    /**
     * <pre>
     *     Master 트랜잭션을 사용하는 메서드
     * </pre>
     * @param serviceProcessExecute
     */
    @Transactional(rollbackFor = Exception.class)
    public void masterTransactionalProcess(ServiceProcessExecute serviceProcessExecute) {
        serviceProcessExecute.execute();
    }
}
