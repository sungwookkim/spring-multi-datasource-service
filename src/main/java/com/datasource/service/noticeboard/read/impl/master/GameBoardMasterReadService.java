package com.datasource.service.noticeboard.read.impl.master;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.GameBoardReadMapper;
import com.datasource.service.noticeboard.read.NoticeBoardReadService;
import com.datasource.service.noticeboard.read.abs.NoticeBoardReadServiceAbs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 *     {@link NoticeBoardReadService} 게임 게시판 읽기 구현체
 * </pre>
 */
@Service
public class GameBoardMasterReadService extends NoticeBoardReadServiceAbs implements NoticeBoardReadService {

    public GameBoardMasterReadService(GameBoardReadMapper gameBoardReadMapper) {
        super(gameBoardReadMapper, NoticeBoardTypeEnum.GAME_BOARD);
    }

    /**
     * {@link NoticeBoardReadService#findPosts(long)} 마스터 트랜잭션 사용
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Posts findPosts(long seq) {
        return super.findPosts(seq);
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardControl(long)} 마스터 트랜잭션 사용
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public NoticeBoardControl findNoticeBoardControl(long referenceSeq) {
        return super.findNoticeBoardControl(referenceSeq);
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardTypeControl()} 마스터 트랜잭션 사용
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<NoticeBoardControl> findNoticeBoardTypeControl() {
        return super.findNoticeBoardTypeControl();
    }
}
