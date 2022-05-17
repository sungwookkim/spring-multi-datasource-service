package com.datasource.service.noticeboard.read;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;

import java.util.List;

/**
 * <pre>
 *     게시판 읽기 서비스 인터페이스
 * </pre>
 */
public interface NoticeBoardReadService {

    /**
     * <pre>
     *     게시물 조회
     * </pre>
     */
    Posts findPosts(long seq);

    /**
     * <pre>
     *     게시판 유형 제어 정보 조회
     *     참조키로 조회
     * </pre>
     */
    NoticeBoardControl findNoticeBoardControl(long referenceSeq);

    /**
     * <pre>
     *     게시판 유형 제어 정보 조회
     *     Type으로 조회
     * </pre>
     */
    List<NoticeBoardControl> findNoticeBoardTypeControl();
}
