package com.datasource.service.noticeboard.read.impl.decorator;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.NoticeBoardReadMapper;
import com.datasource.service.noticeboard.read.NoticeBoardMapperReadService;
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
public class FreeBoardDecoratorReadService implements NoticeBoardMapperReadService {
    private final NoticeBoardMapperReadService freeBoardMasterReadService;
    private final NoticeBoardMapperReadService freeBoardSlaveReadService;

    public FreeBoardDecoratorReadService(NoticeBoardMapperReadService freeBoardMasterReadService
            , NoticeBoardMapperReadService freeBoardSlaveReadService) {
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
     * {@link NoticeBoardMapperReadService#findPosts(NoticeBoardReadMapper, long)} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public Posts findPosts(NoticeBoardReadMapper noticeBoardReadMapper, long seq) {
        return Optional.ofNullable(this.freeBoardSlaveReadService.findPosts(noticeBoardReadMapper, seq))
                .orElseGet(() -> this.freeBoardMasterReadService.findPosts(noticeBoardReadMapper, seq));
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
     * {@link NoticeBoardMapperReadService#findNoticeBoardControl(NoticeBoardReadMapper, long)} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public NoticeBoardControl findNoticeBoardControl(NoticeBoardReadMapper noticeBoardReadMapper, long referenceSeq) {
        return Optional.ofNullable(this.freeBoardSlaveReadService.findNoticeBoardControl(noticeBoardReadMapper, referenceSeq))
                .orElseGet(() -> this.freeBoardMasterReadService.findNoticeBoardControl(noticeBoardReadMapper, referenceSeq));
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardTypeControl()} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public List<NoticeBoardControl> findNoticeBoardTypeControl() {
        return Optional.ofNullable(this.freeBoardSlaveReadService.findNoticeBoardTypeControl())
                .orElseGet(this.freeBoardMasterReadService::findNoticeBoardTypeControl);
    }

    /**
     * {@link NoticeBoardMapperReadService#findNoticeBoardTypeControl(NoticeBoardReadMapper)} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public List<NoticeBoardControl> findNoticeBoardTypeControl(NoticeBoardReadMapper noticeBoardReadMapper) {
        return Optional.ofNullable(this.freeBoardSlaveReadService.findNoticeBoardTypeControl(noticeBoardReadMapper))
                .orElseGet(() -> this.freeBoardMasterReadService.findNoticeBoardTypeControl(noticeBoardReadMapper));
    }
}
