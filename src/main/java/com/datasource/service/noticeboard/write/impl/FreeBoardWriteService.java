package com.datasource.service.noticeboard.write.impl;

import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.write.noticeBoard.FreeBoardWriteMapper;
import com.datasource.service.noticeboard.write.NoticeBoardMapperWriteService;
import com.datasource.service.noticeboard.write.NoticeBoardWriteService;
import com.datasource.service.noticeboard.write.abs.NoticeBoardMapperWriteServiceAbs;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     {@link NoticeBoardWriteService} 자유 게시판 서비스 구현체
 * </pre>
 */
@Service
public class FreeBoardWriteService extends NoticeBoardMapperWriteServiceAbs implements NoticeBoardMapperWriteService {

    public FreeBoardWriteService(FreeBoardWriteMapper freeBoardWriteMapper) {
        super(freeBoardWriteMapper, NoticeBoardTypeEnum.FREE_BOARD);
    }
}
