package com.datasource.service.noticeboard.read.abs;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.NoticeBoardReadMapper;
import com.datasource.service.noticeboard.read.NoticeBoardReadService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link NoticeBoardReadService} 추상 클래스
 */
public abstract class NoticeBoardReadServiceAbs implements NoticeBoardReadService {
    private final NoticeBoardReadMapper noticeBoardReadMapper;
    private final NoticeBoardTypeEnum noticeBoardTypeEnum;

    public NoticeBoardReadServiceAbs(NoticeBoardReadMapper noticeBoardReadMapper
            , NoticeBoardTypeEnum noticeBoardTypeEnum) {
        this.noticeBoardReadMapper = noticeBoardReadMapper;
        this.noticeBoardTypeEnum = noticeBoardTypeEnum;
    }

    /**
     * {@link NoticeBoardReadService#findPosts(long)}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Posts findPosts(long seq) {
        return this.noticeBoardReadMapper.findPosts(seq);
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardControl(long)}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public NoticeBoardControl findNoticeBoardControl(long referenceSeq) {
        return this.noticeBoardReadMapper.findNoticeBoardControl(referenceSeq);
    }

    /**
     * {@link NoticeBoardReadService#findNoticeBoardTypeControl()}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<NoticeBoardControl> findNoticeBoardTypeControl() {
        return this.noticeBoardReadMapper.findNoticeBoardTypeControl(this.noticeBoardTypeEnum.getType());
    }
}
