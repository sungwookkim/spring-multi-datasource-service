package com.datasource.service.noticeboard.read.impl.slave;

import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.GameBoardReadMapper;
import com.datasource.service.noticeboard.read.NoticeBoardReadService;
import com.datasource.service.noticeboard.read.abs.NoticeBoardReadServiceAbs;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     {@link NoticeBoardReadService} 게임 게시판 읽기 구현체
 * </pre>
 */
@Service
public class GameBoardSlaveReadService extends NoticeBoardReadServiceAbs implements NoticeBoardReadService {

    public GameBoardSlaveReadService(GameBoardReadMapper gameBoardReadMapper) {
        super(gameBoardReadMapper, NoticeBoardTypeEnum.GAME_BOARD);
    }
}
