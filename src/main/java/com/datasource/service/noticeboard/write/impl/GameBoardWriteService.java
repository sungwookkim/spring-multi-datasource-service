package com.datasource.service.noticeboard.write.impl;

import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.write.noticeBoard.FreeBoardWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.GameBoardWriteMapper;
import com.datasource.service.noticeboard.write.NoticeBoardWriteService;
import com.datasource.service.noticeboard.write.abs.NoticeBoardWriteServiceAbs;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     {@link NoticeBoardWriteService} 게임 게시판 서비스 구현체
 * </pre>
 */
@Service
public class GameBoardWriteService extends NoticeBoardWriteServiceAbs implements NoticeBoardWriteService {

    public GameBoardWriteService(GameBoardWriteMapper gameBoardWriteMapper) {
        super(gameBoardWriteMapper, NoticeBoardTypeEnum.GAME_BOARD);
    }
}
