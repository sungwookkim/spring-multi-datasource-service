package com.datasource.service.noticeboard.read.impl.decorator;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.service.noticeboard.read.NoticeBoardReadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 *     {@link NoticeBoardReadService} 자유 게시판 읽기 구현체(데코레이터 패턴)
 * </pre>
 */
@Service
public class FreeBoardDecoratorReadService implements NoticeBoardReadService {
    private final NoticeBoardReadService freeBoardMasterReadService;
    private final NoticeBoardReadService freeBoardSlaveReadService;

    public FreeBoardDecoratorReadService(NoticeBoardReadService freeBoardMasterReadService
            , NoticeBoardReadService freeBoardSlaveReadService) {
        this.freeBoardMasterReadService = freeBoardMasterReadService;
        this.freeBoardSlaveReadService = freeBoardSlaveReadService;
    }

    /**
     * {@link NoticeBoardReadService#findPosts(long)} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public Posts findPosts(long seq) {
        return Optional.ofNullable(this.freeBoardSlaveReadService.findPosts(seq))
                .orElseGet(() -> this.freeBoardMasterReadService.findPosts(seq));
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardControl(long)} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public NoticeBoardControl findNoticeBoardControl(long referenceSeq) {
        return Optional.ofNullable(this.freeBoardSlaveReadService.findNoticeBoardControl(referenceSeq))
                .orElseGet(() -> this.freeBoardMasterReadService.findNoticeBoardControl(referenceSeq));
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardTypeControl()} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public List<NoticeBoardControl> findNoticeBoardTypeControl() {
        return Optional.ofNullable(this.freeBoardSlaveReadService.findNoticeBoardTypeControl())
                .orElseGet(this.freeBoardMasterReadService::findNoticeBoardTypeControl);
    }
}
