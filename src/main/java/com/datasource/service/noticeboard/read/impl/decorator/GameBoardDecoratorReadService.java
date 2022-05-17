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
 *     {@link NoticeBoardReadService} 게임 게시판 읽기 구현체
 * </pre>
 */
@Service
public class GameBoardDecoratorReadService implements NoticeBoardReadService {
    private final NoticeBoardReadService gameBoardMasterReadService;
    private final NoticeBoardReadService gameBoardSlaveReadService;

    public GameBoardDecoratorReadService(NoticeBoardReadService gameBoardMasterReadService
            , NoticeBoardReadService gameBoardSlaveReadService) {
        this.gameBoardMasterReadService = gameBoardMasterReadService;
        this.gameBoardSlaveReadService = gameBoardSlaveReadService;
    }

    /**
     * {@link NoticeBoardReadService#findPosts(long)} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public Posts findPosts(long seq) {
        return Optional.ofNullable(this.gameBoardSlaveReadService.findPosts(seq))
                .orElseGet(() -> this.gameBoardMasterReadService.findPosts(seq));
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardControl(long)} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public NoticeBoardControl findNoticeBoardControl(long referenceSeq) {
        return Optional.ofNullable(this.gameBoardSlaveReadService.findNoticeBoardControl(referenceSeq))
                .orElseGet(() -> this.gameBoardMasterReadService.findNoticeBoardControl(referenceSeq));
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardTypeControl()} 마스터/슬레이브 트랜잭션 사용
     */
    @Override
    public List<NoticeBoardControl> findNoticeBoardTypeControl() {
        return Optional.ofNullable(this.gameBoardSlaveReadService.findNoticeBoardTypeControl())
                .orElseGet(this.gameBoardMasterReadService::findNoticeBoardTypeControl);

    }
}
