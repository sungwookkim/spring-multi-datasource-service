package com.datasource.service.noticeboard.read.impl.slave;

import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.FreeBoardReadMapper;
import com.datasource.service.noticeboard.read.NoticeBoardReadService;
import com.datasource.service.noticeboard.read.abs.NoticeBoardReadServiceAbs;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     {@link NoticeBoardReadService} 자유 게시판 읽기 구현체
 * </pre>
 */
@Service
public class FreeBoardSlaveReadService extends NoticeBoardReadServiceAbs implements NoticeBoardReadService {

    public FreeBoardSlaveReadService(FreeBoardReadMapper freeBoardReadMapper) {
        super(freeBoardReadMapper, NoticeBoardTypeEnum.FREE_BOARD);
    }
}
