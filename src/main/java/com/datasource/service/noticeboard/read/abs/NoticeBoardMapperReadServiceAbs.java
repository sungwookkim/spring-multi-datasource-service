package com.datasource.service.noticeboard.read.abs;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.NoticeBoardReadMapper;
import com.datasource.service.noticeboard.read.NoticeBoardMapperReadService;
import com.datasource.service.noticeboard.read.NoticeBoardReadService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 *     {@link NoticeBoardMapperReadService} 추상 클래스
 *     {@link NoticeBoardReadMapper} 인터페이스를 주입 받아 프로세스를 처리.
 * </pre>
 */
public abstract class NoticeBoardMapperReadServiceAbs extends NoticeBoardReadServiceAbs implements NoticeBoardMapperReadService {
    private final NoticeBoardTypeEnum noticeBoardTypeEnum;

    public NoticeBoardMapperReadServiceAbs(NoticeBoardReadMapper noticeBoardReadMapper
            , NoticeBoardTypeEnum noticeBoardTypeEnum) {
        super(noticeBoardReadMapper, noticeBoardTypeEnum);

        this.noticeBoardTypeEnum = noticeBoardTypeEnum;
    }

    /**
     * {@link NoticeBoardMapperReadService#findPosts(NoticeBoardReadMapper, long)}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Posts findPosts(NoticeBoardReadMapper noticeBoardReadMapper, long seq) {
        return noticeBoardReadMapper.findPosts(seq);
    }

    /**
     * {@link NoticeBoardMapperReadService#findNoticeBoardControl(NoticeBoardReadMapper, long)}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public NoticeBoardControl findNoticeBoardControl(NoticeBoardReadMapper noticeBoardReadMapper, long referenceSeq) {
        return noticeBoardReadMapper.findNoticeBoardControl(referenceSeq);
    }

    /**
     * {@link NoticeBoardMapperReadService#findNoticeBoardControl(NoticeBoardReadMapper, long)}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<NoticeBoardControl> findNoticeBoardTypeControl(NoticeBoardReadMapper noticeBoardReadMapper) {
        return noticeBoardReadMapper.findNoticeBoardTypeControl(this.noticeBoardTypeEnum.getType());
    }
}
