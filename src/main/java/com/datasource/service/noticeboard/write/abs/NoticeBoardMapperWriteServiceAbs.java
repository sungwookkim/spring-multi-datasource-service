package com.datasource.service.noticeboard.write.abs;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.NoticeBoardReadMapper;
import com.datasource.repo.mybatis.write.noticeBoard.NoticeBoardWriteMapper;
import com.datasource.service.noticeboard.read.NoticeBoardMapperReadService;
import com.datasource.service.noticeboard.write.NoticeBoardMapperWriteService;
import com.datasource.service.noticeboard.write.NoticeBoardWriteService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 *     {@link NoticeBoardMapperWriteService} 추상 클래스
 *     {@link NoticeBoardWriteMapper} 인터페이스를 주입 받아 프로세스를 처리.
 * </pre>
 */
public abstract class NoticeBoardMapperWriteServiceAbs extends NoticeBoardWriteServiceAbs implements NoticeBoardMapperWriteService {
    private final NoticeBoardTypeEnum noticeBoardTypeEnum;

    public NoticeBoardMapperWriteServiceAbs(NoticeBoardWriteMapper noticeBoardWriteMapper, NoticeBoardTypeEnum noticeBoardTypeEnum) {
        super(noticeBoardWriteMapper, noticeBoardTypeEnum);
        
        this.noticeBoardTypeEnum = noticeBoardTypeEnum;
    }

    /**
     * <pre>
     *     {@link NoticeBoardMapperWriteService#insertPosts(NoticeBoardWriteMapper, Posts)}
     *     게시글 / 게시글 유형 제어 정보 저장
     * </pre>
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public long insertPosts(NoticeBoardWriteMapper noticeBoardWriteMapper, Posts posts) {
        noticeBoardWriteMapper.insertPosts(posts);

        NoticeBoardControl noticeBoardControl = new NoticeBoardControl(posts.getSeq(), this.noticeBoardTypeEnum);
        noticeBoardWriteMapper.insertPostsControl(noticeBoardControl);

        return posts.getSeq();
    }
}
