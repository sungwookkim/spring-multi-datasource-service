package com.datasource.service.noticeboard.write;

import com.datasource.entity.noticeBoard.Posts;

/**
 * <pre>
 *     게시판 저장 서비스 인터페이스
 * </pre>
 */
public interface NoticeBoardWriteService {

    /**
     * <pre>
     *     게시물 저장
     * </pre>
     */
    long insertPosts(Posts posts);
}
