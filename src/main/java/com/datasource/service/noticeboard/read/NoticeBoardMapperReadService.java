package com.datasource.service.noticeboard.read;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.repo.mybatis.read.noticeBoard.NoticeBoardReadMapper;

import java.util.List;

/**
 * <pre>
 *     게시판 읽기 서비스 인터페이스
 *     {@link NoticeBoardReadMapper} 인터페이스를 주입 받아 프로세스를 처리.
 * </pre>
 */
public interface NoticeBoardMapperReadService extends NoticeBoardReadService {

    /**
     * <pre>
     *     게시물 조회
     * </pre>
     */
    Posts findPosts(NoticeBoardReadMapper noticeBoardReadMapper, long seq);

    /**
     * <pre>
     *     게시판 유형 제어 정보 조회
     *     참조키로 조회
     * </pre>
     */
    NoticeBoardControl findNoticeBoardControl(NoticeBoardReadMapper noticeBoardReadMapper, long referenceSeq);

    /**
     * <pre>
     *     게시판 유형 제어 정보 조회
     *     Type으로 조회
     * </pre>
     */
    List<NoticeBoardControl> findNoticeBoardTypeControl(NoticeBoardReadMapper noticeBoardReadMapper);
}
