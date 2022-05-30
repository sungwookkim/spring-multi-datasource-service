package com.datasource.repo.mybatis.write.noticeBoard;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.repo.core.noticeBoard.write.NoticeBoardWrite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * <pre>
 *     {@link NoticeBoardWriteMapper} Mybatis용 게임 게시판
 * </pre>
 */
@Mapper
public interface GameBoardWriteMapper extends NoticeBoardWriteMapper {

    /**
     * <pre>
     *     {@link NoticeBoardWrite#insertPosts(Posts)} 게임 게시판 글 저장
     * </pre>
     *
     */
    @Insert("INSERT INTO game_board(member_id, text) VALUES(#{memberId}, #{text})")
    @Options(useGeneratedKeys = true, keyProperty = "seq", keyColumn = "seq")
    @Override
    void insertPosts(Posts posts);
}
