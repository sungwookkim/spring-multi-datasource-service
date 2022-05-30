package com.datasource.repo.mybatis.write.noticeBoard;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.repo.core.noticeBoard.write.NoticeBoardWrite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * <pre>
 *     {@link NoticeBoardWriteMapper} Mybatis용 자유 게시판
 * </pre>
 */
@Mapper
public interface FreeBoardWriteMapper extends NoticeBoardWriteMapper {

    /**
     * {@link NoticeBoardWrite#insertPosts(Posts)} 자유 게시판 글 저장
     */
    @Insert("INSERT INTO free_board(member_id, text) VALUES(#{memberId}, #{text})")
    @Options(useGeneratedKeys = true, keyProperty = "seq", keyColumn = "seq")
    @Override
    void insertPosts(Posts posts);
}
