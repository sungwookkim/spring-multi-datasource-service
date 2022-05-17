package com.datasource.repo.core.noticeBoard.read;

import com.datasource.entity.noticeBoard.Posts;

/**
 * <pre>
 *     게시판 읽기 인터페이스
 * </pre>
 */
public interface NoticeBoardRead {

    /**
     * <pre>
     *     게시물 조회 메서드
     * </pre>
     */
    Posts findPosts(long seq);
}
