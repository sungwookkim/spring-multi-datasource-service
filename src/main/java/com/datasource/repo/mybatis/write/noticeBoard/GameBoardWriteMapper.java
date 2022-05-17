package com.datasource.repo.mybatis.write.noticeBoard;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.repo.core.noticeBoard.read.NoticeBoardRead;
import com.datasource.repo.core.noticeBoard.write.NoticeBoardWrite;
import com.datasource.repo.mybatis.read.noticeBoard.NoticeBoardReadMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * <pre>
 *     {@link NoticeBoardWriteMapper} Mybatis용 게임 게시판
 * </pre>
 */
@Mapper
public interface GameBoardWriteMapper extends NoticeBoardWriteMapper {

    /**
     * {@link NoticeBoardWrite#insertPosts(Posts)} 게임 게시판 글 저장
     */
    @Insert("INSERT INTO game_board(member_id, text) VALUES(#{memberId}, #{text})")
    @Options(useGeneratedKeys = true, keyProperty = "seq", keyColumn = "seq")
    @Override
    long insertPosts(Posts posts);
}
