package com.datasource.service.noticeboard.write.abs;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.write.noticeBoard.NoticeBoardWriteMapper;
import com.datasource.service.noticeboard.write.NoticeBoardWriteService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 *     {@link NoticeBoardWriteService} Mybatis용
 * </pre>
 */
public abstract class NoticeBoardWriteServiceAbs implements NoticeBoardWriteService {
    private final NoticeBoardWriteMapper noticeBoardWriteMapper;
    private final NoticeBoardTypeEnum noticeBoardTypeEnum;

    public NoticeBoardWriteServiceAbs(NoticeBoardWriteMapper noticeBoardWriteMapper, NoticeBoardTypeEnum noticeBoardTypeEnum) {
        this.noticeBoardWriteMapper = noticeBoardWriteMapper;
        this.noticeBoardTypeEnum = noticeBoardTypeEnum;
    }

    /**
     * <pre>
     *     {@link NoticeBoardWriteService#insertPosts(Posts)}
     *     게시글 / 게시글 유형 제어 정보 저장
     * </pre>
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public long insertPosts(Posts posts) {
        this.noticeBoardWriteMapper.insertPosts(posts);
        this.noticeBoardWriteMapper.insertPostsControl(new NoticeBoardControl(posts.getSeq(), this.noticeBoardTypeEnum));

        return posts.getSeq();
    }

}
