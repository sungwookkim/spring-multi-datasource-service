package com.datasource.service.noticeboard.write;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.repo.mybatis.write.noticeBoard.NoticeBoardWriteMapper;

/**
 * <pre>
 *     게시판 저장 서비스 인터페이스
 *     {@link NoticeBoardWriteMapper} 인터페이스를 주입 받아 프로세스를 처리.
 * </pre>
 */
public interface NoticeBoardMapperWriteService extends NoticeBoardWriteService {

    /**
     * <pre>
     *     게시물 저장
     * </pre>
     */
    long insertPosts(NoticeBoardWriteMapper noticeBoardWriteMapper, Posts posts);
}
