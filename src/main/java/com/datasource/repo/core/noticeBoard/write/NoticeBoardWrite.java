package com.datasource.repo.core.noticeBoard.write;

import com.datasource.entity.noticeBoard.Posts;

/**
 * <pre>
 *     게시판 저장 인터페이스
 * </pre>
 */
public interface NoticeBoardWrite {

    /**
     * <pre>
     *     게시물 저장 메서드
     * </pre>
     */
    void insertPosts(Posts posts);
}
